package com.dilo.test;

import com.dilo.domain.Cart;
import com.dilo.domain.CartItem;
import com.dilo.service.OrderService;
import com.dilo.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(500)));
        cart.addItem(new CartItem(1,"java",2,new BigDecimal(500)));
        cart.addItem(new CartItem(2,"c语言",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(2000)));

        OrderService orderService = new OrderServiceImpl();
        System.out.println(cart);
        String order = orderService.createOrder(cart, 1);
        System.out.println("订单号是："+order);
        System.out.println(cart);
    }
}