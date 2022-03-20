package com.dilo.gmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dilo.gmall.model.enums.ProcessStatus;
import com.dilo.gmall.model.order.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<OrderInfo> {

    /**
     * 保存订单
     * @param orderInfo
     * @return
     */
    Long saveOrderInfo(OrderInfo orderInfo);

    /**
     * 产生流水交易号
     * @param userId
     * @return
     */
    String getTradeNo(String userId);

    /**
     * 比较流水交易好
     * @param userId 获取混村中的流水号
     * @param tradeNo 页面传过来的流水交易号
     * @return
     */
    boolean checkTradeNo(String userId,String tradeNo);

    /**
     * 删除流水号
     * @param userId
     */
    void deleteTradeNo(String userId);

    /**
     * 验证库存
     * @param skuId
     * @param skuNum
     * @return
     */
    boolean checkStock(Long skuId, Integer skuNum);

    void execExpiredOrder(Long orderId);

    /**
     * 根据订单Id 更新订单状态，订单进度状态
     * @param orderId
     * @param processStatus
     */
    void updateOrderStatus(Long orderId, ProcessStatus processStatus);

    /**
     * 根据订单Id 查询订单信息
     * @param orderId
     * @return
     */


    /**
     * 根据订单Id 查询订单信息
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfo(Long orderId);

    /**
     * 根据订单id,发送消息给库存！,并修改订单状态
     * @param orderId
     */
    void sendOrderStatus(Long orderId);
    Map initWareOrder(OrderInfo orderInfo);


    /**
     * 拆单方法
     * @param orderId
     * @param wareSkuMap
     * @return
     */
    List<OrderInfo> orderSplit(String orderId, String wareSkuMap);

    /**
     * 关闭过期订单
     * @param orderId
     * @param flag 是否需要关闭paymentInmfo
     */
    void execExpiredOrder(Long orderId, String flag);
}
