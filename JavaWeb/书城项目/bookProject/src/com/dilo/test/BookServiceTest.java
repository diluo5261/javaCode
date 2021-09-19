package com.dilo.test;

import com.dilo.domain.Book;
import com.dilo.service.BookService;
import com.dilo.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"四驱兄弟","卑鄙的日本",new BigDecimal(0.1),96,25));
    }

    @Test
    public void deleteBook() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"哆啦阿梦","无耻的日本",new BigDecimal(0.3),56,48));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for (Book book :bookService.queryBooks()) {
            System.out.println(book);

        }
    }
}