package com.dilo.gmall.order.controller;

import com.alibaba.fastjson.JSON;
import com.dilo.gmall.cart.client.CartFeignClient;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.common.util.AuthContextHolder;
import com.dilo.gmall.model.cart.CartInfo;
import com.dilo.gmall.model.order.OrderDetail;
import com.dilo.gmall.model.order.OrderInfo;
import com.dilo.gmall.model.user.UserAddress;
import com.dilo.gmall.order.service.OrderService;
import com.dilo.gmall.product.client.ProductFeignClient;
import com.dilo.gmall.user.client.UserFeignClient;
import com.netflix.ribbon.proxy.annotation.Http;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Api(tags = "订单模块")
@RestController
@RequestMapping("api/order")
public class OrderApiController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private CartFeignClient cartFeignClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductFeignClient productFeignClient;

    //远程调用地址
    @ApiOperation("确认订单")
    @GetMapping("auth/trade")
    public Result<Map<String, Object>> trade(HttpServletRequest request){

        //获取用户id
        String userId = AuthContextHolder.getUserId(request);
        HashMap<String, Object> map = new HashMap<>();
        //远程调用才能获取到收获地址列表
        List<UserAddress> userAddressList = userFeignClient.findUserAddressListByUserId(userId);

        //获取送货清单
        List<CartInfo> cartCheckedList = cartFeignClient.getCartCheckedList(userId);
        //声明一个订单明细集合
        ArrayList<OrderDetail> detailList = new ArrayList<>();
        int totalNum = 0;
        for (CartInfo cartInfo : cartCheckedList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setSkuId(cartInfo.getSkuId());
            orderDetail.setSkuName(cartInfo.getSkuName());
            orderDetail.setImgUrl(cartInfo.getImgUrl());
            orderDetail.setSkuNum(cartInfo.getSkuNum());
            orderDetail.setOrderPrice(cartInfo.getSkuPrice());
            orderDetail.setCreateTime(new Date());
            totalNum += cartInfo.getSkuNum();
            detailList.add(orderDetail);
        }

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderDetailList(detailList);

        map.put("totalAmount",orderInfo.getTotalAmount());
        map.put("totalNum",detailList.size());
        map.put("userAddressList", userAddressList);
        map.put("detailArrayList",detailList);

        String tradeNo = orderService.getTradeNo(userId);
        map.put("tradeNo",tradeNo);

        return Result.ok(map);
    }

    //使用异步编排
    @PostMapping("auth/submitOrder")
    public Result submitOrder(@RequestBody OrderInfo orderInfo,HttpServletRequest request){

        String userId = AuthContextHolder.getUserId(request);
        orderInfo.setUserId(Long.parseLong(userId));
        String tradeNo = request.getParameter("tradeNo");
        boolean result = orderService.checkTradeNo(userId, tradeNo);

        if (!result){
            //比较失败,返回失败
            return Result.fail().message("不能无刷新回退提交订单");
        }
        orderService.deleteTradeNo(userId);

        List<String> errorList = new ArrayList<>();
        //执行异步编排的组合
        List<CompletableFuture> futureList = new ArrayList<>();

        List<OrderDetail> orderDetailList = orderInfo.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            //验证库存

            //开线程验证库存
            CompletableFuture<Void> checkStockCompletableFuture  = CompletableFuture.runAsync(() -> {
                boolean flag = orderService.checkStock(orderDetail.getSkuId(), orderDetail.getSkuNum());
                if (!flag) {
                    errorList.add(orderDetail.getSkuName() + "库存不足");
                }
            });
            futureList.add(checkStockCompletableFuture);


            //验证价格
            CompletableFuture<Void> checkPriceCompletableFuture   = CompletableFuture.runAsync(() -> {
                //订单价格
                BigDecimal orderPrice = orderDetail.getOrderPrice();
                //实时价格
                BigDecimal skuPrice = productFeignClient.getSkuPrice(orderDetail.getSkuId());

                //判断
                if (orderPrice.compareTo(skuPrice) != 0){
                    //价格有变动,需要更新价格
                    cartFeignClient.loadCartCache(userId);
                    errorList.add(orderDetail.getSkuName()+"价格有变动");
                }
            });
            futureList.add(checkPriceCompletableFuture);
        }

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
        if (errorList.size()>0){
            return Result.fail().message(StringUtils.join(errorList,","));
        }

        //调用服务层方法
        Long orderId = orderService.saveOrderInfo(orderInfo);
        //返回订单id
        return Result.ok(orderId);
    }


    //没有使用异步编排
   /* @PostMapping("auth/submitOrder")
    public Result submitOrder(@RequestBody OrderInfo orderInfo,HttpServletRequest request){


        String userId = AuthContextHolder.getUserId(request);
        orderInfo.setUserId(Long.parseLong(userId));
        String tradeNo = request.getParameter("tradeNo");
        boolean result = orderService.checkTradeNo(userId, tradeNo);

        if (!result){
            //比较失败,返回失败
            return Result.fail().message("不能无刷新回退提交订单");
        }

        orderService.deleteTradeNo(userId);

        List<OrderDetail> orderDetailList = orderInfo.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            //验证库存
            boolean flag = orderService.checkStock(orderDetail.getSkuId(), orderDetail.getSkuNum());
            if (!flag ){
                return Result.fail().message(orderDetail.getSkuName()+"库存不足");
            }

            //验证价格
            //订单价格
            BigDecimal orderPrice = orderDetail.getOrderPrice();
            //实时价格
            BigDecimal skuPrice = productFeignClient.getSkuPrice(orderDetail.getSkuId());

            //判断
            if (orderPrice.compareTo(skuPrice) != 0){
                //价格有变动,需要更新价格
                cartFeignClient.loadCartCache(userId);
                return Result.fail().message(orderDetail.getSkuName()+"价格有变动");
            }
        }
        //调用服务层方法
        Long orderId = orderService.saveOrderInfo(orderInfo);
        //返回订单id
        return Result.ok(orderId);
    }*/

    @GetMapping("inner/getOrderInfo/{orderId}")
    public OrderInfo getOrderInfo(@PathVariable Long orderId){
        return orderService.getOrderInfo(orderId);
    }

    @PostMapping("orderSplit")
    public String orderSplit(HttpServletRequest request){
        String orderId = request.getParameter("orderId");
        String wareSkuMap = request.getParameter("wareSkuMap");

        List<OrderInfo> orderInfoList = orderService.orderSplit(orderId,wareSkuMap);

        ArrayList<Map> maps = new ArrayList<>();
        //循环遍历
        for (OrderInfo orderInfo : orderInfoList) {
            //orderInfo 转换为map
            Map map = orderService.initWareOrder(orderInfo);
            maps.add(map);
        }

        //返回数据
        return JSON.toJSONString(maps);

    }
}
