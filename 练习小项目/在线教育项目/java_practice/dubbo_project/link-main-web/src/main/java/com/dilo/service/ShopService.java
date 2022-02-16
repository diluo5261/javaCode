package com.dilo.service;

import com.dilo.domain.Order;

public interface ShopService {

    Order buyGoods(Integer userId, String goodName, float price, int amount);
}
