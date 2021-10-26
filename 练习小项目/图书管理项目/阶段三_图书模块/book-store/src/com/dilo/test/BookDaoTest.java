package com.dilo.test;

import com.dilo.dao.BookDao;
import com.dilo.dao.Impl.BookDaoImpl;
import com.dilo.domain.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        int i = bookDao.addBook(
                new Book(null, "民法典", new BigDecimal(956), "中国", 10525, 565, null));

        if (i != -1){
            System.out.println("成功插入"+i+"条语句");
        }

    }

    @Test
    public void deleteById() {
        int i = bookDao.deleteById(29);

        if (i != -1){
            System.out.println("删除成功!");
        }
    }

    @Test
    public void updateBook() {
        int flag = bookDao.updateBook(new Book(29,"新民法典",new BigDecimal(956),"政府",999,666,null));

        if (flag != -1){
            System.out.println("修改成功!");
        }
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(29));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();

        for (Book book : books){
            System.out.println(book);
        }

    }
}