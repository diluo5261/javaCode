package com.dilo.service.impl;

import com.dilo.dao.BookDao;
import com.dilo.service.IOperation;

import java.util.Scanner;

public class ReturnOperation implements IOperation {
    @Override
    public void work() {
        System.out.println("归还 书籍");
        System.out.println("请输入要归还书籍的ID:");
        Integer bookId = new Scanner(System.in).nextInt();

        BookDao bookDao = new BookDao();
        boolean ret = bookDao.borrowBookById(bookId);

        if (ret ){
            System.out.println("书籍借阅成功!");

        }else{
            System.out.println("书籍借阅失败");
        }

    }

}

