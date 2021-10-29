package com.dilo.test;

import com.dilo.dao.Impl.OrderItemDaoImpl;
import com.dilo.domain.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();

        int res = orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),"1234567890"));
        if (res >0){
            System.out.println("添加成功");
        }
    }
}