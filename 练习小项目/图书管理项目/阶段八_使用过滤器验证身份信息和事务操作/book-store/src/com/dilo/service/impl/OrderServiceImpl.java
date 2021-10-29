package com.dilo.service.impl;

import com.dilo.dao.BookDao;
import com.dilo.dao.Impl.BookDaoImpl;
import com.dilo.dao.Impl.OrderDaoImpl;
import com.dilo.dao.Impl.OrderItemDaoImpl;
import com.dilo.dao.OrderDao;
import com.dilo.dao.OrderItemDao;
import com.dilo.domain.*;
import com.dilo.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {

        //订单号 唯一性，一般是时间戳，但是在双11的时候，很可能回重复，因此在后面加上用户的id
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);

        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
           //获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            //转化未每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),cartItem.getPrice(),orderId);

            //更新库存和销量
            Book book =  bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+ cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);

            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);
        }

        //清空购物城
        cart.clean();

        return orderId;
    }
}
