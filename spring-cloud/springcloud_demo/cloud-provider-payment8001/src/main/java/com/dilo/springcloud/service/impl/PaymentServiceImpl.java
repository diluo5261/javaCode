package com.dilo.springcloud.service.impl;

import com.dilo.springcloud.bean.Book;
import com.dilo.springcloud.dao.PaymentDao;
import com.dilo.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int creat(Book book) {
        return paymentDao.creat(book);

    }

    @Override
    public Book getParamById(int id) {
        return paymentDao.getParamById(id);
    }
}
