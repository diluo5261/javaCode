package com.dilo.test;

import com.dilo.dao.BookDao;
import com.dilo.dao.impl.BookDaoImpl;
import com.dilo.domain.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"哆啦A梦","日本",new BigDecimal(999),8888,8888));

    }

    @Test
    public void deleteBook() {
        bookDao.deleteBookById(20);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"哆啦美","低贱的日本",new BigDecimal(20),95659,6666));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for(Book book : bookDao.queryBooks()){
            System.out.println(book);
        }
    }
}