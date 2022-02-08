package com.dilo.springcloud.dao;

import com.dilo.springcloud.bean.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PaymentDao {

    int creat(Book book);
    Book getParamById(int id);
}
