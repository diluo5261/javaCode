package com.dilo.service.impl;

import com.dilo.dao.BookDao;
import com.dilo.service.IOperation;

import java.util.Scanner;

public class DelOperation implements IOperation {
    @Override
    public void work() {
        System.out.println("删除书籍!");
        System.out.println("请输入要删除书籍的id");
       Integer bookId = new Scanner(System.in).nextInt();

        BookDao bookDao = new BookDao();
        boolean ret = bookDao.delBookById(bookId);

        if (ret ){
            System.out.println("书籍"+bookId+"删除成功!");
        }else{
            System.out.println("书籍删除失败!");
        }

    }
}
