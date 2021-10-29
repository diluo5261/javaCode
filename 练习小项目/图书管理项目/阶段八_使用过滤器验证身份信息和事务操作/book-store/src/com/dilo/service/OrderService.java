package com.dilo.service;

import com.dilo.domain.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
