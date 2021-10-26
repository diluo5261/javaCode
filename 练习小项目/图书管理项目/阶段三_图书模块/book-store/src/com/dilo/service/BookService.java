package com.dilo.service;

import com.dilo.domain.Book;

import java.util.List;

public interface BookService {

    public void add(Book book);

    public void deleteBookBuId(Integer id);

    public void updateBook(Book book);

    public Book queryBookBuId(Integer id);

    public List<Book> queryBooks();
}
