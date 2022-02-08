package com.dilo.springcloud.service;

import com.dilo.springcloud.bean.Book;

public interface PaymentService {
    int creat(Book book); //写
    Book getParamById(int id);  //读取
}
