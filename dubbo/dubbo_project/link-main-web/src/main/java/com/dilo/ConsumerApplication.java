package com.dilo;

import com.dilo.domain.Order;
import com.dilo.service.impl.ShopServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("main-consume.xml");
        //从容器中获取对象
        ShopServiceImpl shopServiceImpl = (ShopServiceImpl)context.getBean("shopService");
        Order order = shopServiceImpl.buyGoods(1, "thinkpad", 5000.0f, 1);
        System.out.println("购买的订单信息："+order);
    }
}
