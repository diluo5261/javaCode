package com.dilo.gmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dilo.gmall.model.order.OrderInfo;

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


    /**
     * 根据订单Id 查询订单信息
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfo(Long orderId);



}
