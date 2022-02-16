package com.dilo.service.impl;

import com.dilo.domain.Order;
import com.dilo.service.OrderService;

import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    @Override
    public Order creatOrder(Integer id, String goodsName, float price, int amount) {

        //把订单记录到数据库中
        Order order = new Order();
        order.setId(UUID.randomUUID().toString().replaceAll("-",""));
        order.setGoodName(goodsName);
        order.setPrice(price);
        order.setAmount(amount);
        return order;

    }
}
