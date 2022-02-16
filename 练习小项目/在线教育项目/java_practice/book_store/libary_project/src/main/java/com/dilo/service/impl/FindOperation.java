package com.dilo.service.impl;

import com.dilo.dao.BookDao;
import com.dilo.domainn.Book;
import com.dilo.service.IOperation;

import java.util.List;
import java.util.Scanner;

public class FindOperation implements IOperation {
    @Override
    public void work() {
        System.out.println("根据名字查找数据");
        System.out.print("请输入你要查找的书籍的名字:");
        String bookName = new Scanner(System.in).nextLine();
        BookDao bookDao = new BookDao();
        List<Book> bookList = bookDao.selectByName(bookName);

        for (Book book : bookList){
            System.out.println(book);
        }

        System.out.println("书籍查找完毕");

    }
}
