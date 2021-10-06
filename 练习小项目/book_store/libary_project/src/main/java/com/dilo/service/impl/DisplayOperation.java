package com.dilo.service.impl;


import com.dilo.dao.BookDao;
import com.dilo.domainn.Book;
import com.dilo.service.IOperation;

import java.util.List;

//查看数据列表
public class DisplayOperation implements IOperation {
    @Override
    public void work() {
        System.out.println("展示所有书籍");
        BookDao bookDao = new BookDao();
        List<Book> list = bookDao.selectAll();

        for (Book book : list){
            System.out.println(book);
        }
        System.out.println("展示所有书籍完毕!");
    }
}
