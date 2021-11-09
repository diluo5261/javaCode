package com.dilo.service.impl;

import com.dilo.domain.Address;
import com.dilo.domain.Order;
import com.dilo.service.OrderService;
import com.dilo.service.ShopService;
import com.dilo.service.UserInfoService;
import com.dilo.vo.Goods;

import java.util.List;

public class ShopServiceImpl implements ShopService {

    //定义远程接口作为属性
    private OrderService orderService;
    private UserInfoService userInfoService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public Order buyGoods(Integer userId, Goods goods) {
        //调用远程服务:服务提供者的订单功能
        Order order = orderService.creatOrder(userId,goods.getName(), goods.getPrice(), goods.getAmount());

        return order;
    }

    @Override
    public List<Address> getAddress(Integer userId) {
        //调用UserService服务提供者
        return userInfoService.queryAddress(userId);

    }
}
