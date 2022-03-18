package com.dilo.gmall.cart.controller;

import com.dilo.gmall.cart.service.CartInfoService;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.common.util.AuthContextHolder;
import com.dilo.gmall.model.cart.CartInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api(tags = "购物车模块")
@RestController
@RequestMapping("api/cart")
public class CartApiController {
    //注入服务层
    @Autowired
    private CartInfoService cartInfoService;

    @ApiOperation("添加商品到购物车")
    @PostMapping("addToCart/{skuId}/{skuNum}")
    public Result addToCart(@PathVariable("skuId") Long skuId,
                            @PathVariable("skuNum") Integer skuNum,
                            HttpServletRequest request) {

        //缺少一个用户Id,在网络已经获取过了
        String userId = AuthContextHolder.getUserId(request);
        //两种情况下可以加入购物成
        //1.登录情况下,有用户id,
        if (StringUtils.isEmpty(userId)) {
            //2.没有登录的情况下,没有用户id,要给一个临时的用户id
            userId = AuthContextHolder.getUserTempId(request);
        }

        cartInfoService.addToCart(skuId, userId, skuNum);

        return Result.ok();
    }

    @ApiOperation("查询购物车列表")
    @GetMapping("cartList")
    public Result cartList(HttpServletRequest request) {

        //缺少一个用户Id,在网络已经获取过了
        String userId = AuthContextHolder.getUserId(request);
        //1.登录情况下,有用户id,

        //2.没有登录的情况下,没有用户id,要给一个临时的用户id
        String userTempId = AuthContextHolder.getUserTempId(request);


        //调用服务层查询方法
        List<CartInfo> cartList = cartInfoService.getCartList(userId, userTempId);

        return Result.ok(cartList);
    }

    @ApiOperation("购物车选中状态变更")
    @GetMapping("checkCart/{skuId}/{isChecked}")
    public Result checkCart(@PathVariable(value = "skuId") Long skuId,
                            @PathVariable(value = "isChecked") Integer isChecked,
                            HttpServletRequest request) {

        String userId = AuthContextHolder.getUserId(request);
        if (StringUtils.isEmpty(userId)){
            userId = AuthContextHolder.getUserTempId(request);
        }

        cartInfoService.checkCart(userId, isChecked, skuId);
        return Result.ok();

    }

    @ApiOperation("删除购物车")
    @DeleteMapping("deleteCart/{skuId}")
    public Result deleteCart(@PathVariable("skuId") Long skuId,HttpServletRequest request) {
        String userId = AuthContextHolder.getUserId(request);
        if (StringUtils.isEmpty(userId)){
            userId = AuthContextHolder.getUserTempId(request);
        }
        cartInfoService.deleteCart(userId,skuId);

        return Result.ok();
    }


    @ApiOperation("根据用户Id 查询购物车列表")
    @GetMapping("getCartCheckedList/{userId}")
    public List<CartInfo> getCartCheckedList(@PathVariable(value = "userId") String userId) {
        return cartInfoService.getCartCheckedList(userId);
    }

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("loadCartCache/{userId}")
    public Result loadCartCache(@PathVariable("userId") String userId) {
        cartInfoService.loadCartCache(userId);
        return Result.ok();
    }



}
