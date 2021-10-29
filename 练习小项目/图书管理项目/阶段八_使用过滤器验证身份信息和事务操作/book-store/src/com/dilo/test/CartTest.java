package com.dilo.test;

import com.dilo.domain.Cart;
import com.dilo.domain.CartItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(500)));
        cart.addItem(new CartItem(1,"java",2,new BigDecimal(500)));
        cart.addItem(new CartItem(2,"c语言",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(2000)));

        System.out.println(cart);


    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(500)));
        cart.addItem(new CartItem(1,"java",2,new BigDecimal(500)));
        cart.addItem(new CartItem(2,"c语言",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(2000)));
        System.out.println(cart);
        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clean() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(500)));
        cart.addItem(new CartItem(1,"java",2,new BigDecimal(500)));
        cart.addItem(new CartItem(2,"c语言",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(2000)));
        System.out.println(cart);
        cart.clean();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(500)));
        cart.addItem(new CartItem(1,"java",2,new BigDecimal(500)));
        cart.addItem(new CartItem(2,"c语言",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(2000)));
        System.out.println(cart);
        cart.updateCount(3,10);
        System.out.println(cart);
    }
}