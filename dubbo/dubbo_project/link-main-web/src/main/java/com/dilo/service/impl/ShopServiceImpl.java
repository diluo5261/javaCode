package com.dilo.service.impl;

import com.dilo.domain.Order;
import com.dilo.service.OrderService;
import com.dilo.service.ShopService;

public class ShopServiceImpl implements ShopService {
    private OrderService orderService;

    //spring的IOC设值注入
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Order buyGoods(Integer userId, String goodName, float price, int amount) {

    //    购买商品生成订单，订单生成由服务提供者orderService-provider完成的
        //需要调用服务提供者的方法createOrder（）
        return orderService.creatOrder(userId, goodName, price, amount);
    }
}
