package com.dilo.service.impl;

import com.dilo.dao.BookDao;
import com.dilo.service.IOperation;

import java.util.Scanner;

public class BorrowOperation implements IOperation {
    @Override
    public void work() {
        System.out.println("借阅书籍");
        System.out.println("请输入要借阅书籍的ID:");
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
