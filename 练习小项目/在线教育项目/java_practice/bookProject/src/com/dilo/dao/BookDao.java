package com.dilo.dao;

import com.dilo.domain.Book;

import java.util.List;

public interface BookDao {

    int addBook(Book book);

    int deleteBookById(Integer id);

    int updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();


}
