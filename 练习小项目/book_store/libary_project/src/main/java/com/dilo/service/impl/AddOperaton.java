package com.dilo.service.impl;

import com.dilo.dao.BookDao;
import com.dilo.domainn.Book;
import com.dilo.service.IOperation;

import java.util.Scanner;

public class AddOperaton implements IOperation {

    @Override
    public void work() {
        System.out.println("新增书籍!");
        Scanner scan = new Scanner(System.in);

        System.out.print("请输入书籍的名字");
        String bookName = scan.nextLine();
        System.out.print("请输入书籍的作者");
        String author = scan.nextLine();
        System.out.print("请输入书籍的价格");
        int price = scan.nextInt();
        System.out.print("请输入书籍的类型");
        String type = scan.nextLine();

        Book book = new Book();
        book.setName(bookName);
        book.setAuthor(author);
        book.setPrice(price);
        book.setType(type);

        BookDao bookDao = new BookDao();
        boolean ret = bookDao.add(book);

        if (ret ){
            System.out.println("新增成功!");

        }else{
            System.out.println("新增失败!");
        }


    }
}
