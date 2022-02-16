package com.dilo.service;

import com.dilo.domain.Book;

import java.util.List;

public interface BookService {

    boolean save(Book book);

    boolean update(Book book);

    boolean delete(Integer id);

    Book getById(Integer id);

    List<Book> getAll();
}
