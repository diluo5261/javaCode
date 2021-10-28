package com.dilo.test;

import com.dilo.domain.Book;
import com.dilo.domain.Page;
import com.dilo.service.BookService;
import com.dilo.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void add() {
        bookService.add(new Book(null,"民法典",new BigDecimal(856),"政府",459,856,null));
    }

    @Test
    public void deleteBookBuId() {
        bookService.deleteBookBuId(28);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(30,"新民法典",new BigDecimal(856),"中国政府",459,856,null));

    }

    @Test
    public void queryBookBuId() {
        System.out.println(bookService.queryBookBuId(30));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();

        for (Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }
}