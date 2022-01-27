package com.dilo.dao;

import com.dilo.domain.Book;

import java.util.List;

public interface BookDao {

    Book findBookByName(String name);

    List<Book> findAll();
}
