package com.dilo.controller;

import com.dilo.domain.Book;
import com.dilo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class SSMPController {

    @Autowired
    private IBookService bookService;

    @RequestMapping("/getall")
    public List<Book> getAll(){
        List<Book> bookList = bookService.list();
        System.out.println(bookList);
        return bookList;
    }

    public 


}
