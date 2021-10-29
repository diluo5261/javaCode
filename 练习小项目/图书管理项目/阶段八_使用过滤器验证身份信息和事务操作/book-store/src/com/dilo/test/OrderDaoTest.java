package com.dilo.test;

import com.dilo.dao.Impl.OrderDaoImpl;
import com.dilo.dao.OrderDao;
import com.dilo.domain.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        int res = orderDao.saveOrder(new Order("1234567890",new Date(),new BigDecimal(100),0,1));
        if (res != 0){
            System.out.println("保存成功！");
        }

    }
}