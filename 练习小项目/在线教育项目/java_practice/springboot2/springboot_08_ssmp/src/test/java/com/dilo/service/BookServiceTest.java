package com.dilo.service;

import com.dilo.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private IBookService iBookService;

    @Test
    void testGetById(){
        Book book = iBookService.getById(3);
        System.out.println(book);
    }
}
