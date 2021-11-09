package com.dilo.service;

import com.dilo.domain.Address;
import com.dilo.domain.Order;
import com.dilo.vo.Goods;

import java.util.List;

public interface ShopService {
    //购买商品,生成订单
    public Order buyGoods(Integer userId, Goods goods);

    //获取用户的地址列表
    public List<Address> getAddress(Integer userId);
}
