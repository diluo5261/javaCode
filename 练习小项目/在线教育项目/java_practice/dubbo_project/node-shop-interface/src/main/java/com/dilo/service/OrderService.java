package com.dilo.service;

import com.dilo.domain.Order;

public interface OrderService {

    //创建订单
    Order creatOrder(Integer id, String goodsName, float price, int amount);
}
