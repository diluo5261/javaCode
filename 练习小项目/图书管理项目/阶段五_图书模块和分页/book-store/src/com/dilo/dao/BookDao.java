package com.dilo.dao;

import com.dilo.domain.Book;

import java.util.List;

public interface BookDao {

    public int addBook(Book book);

    int deleteById(Integer id);

    int updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Integer queryForPageTotal();

    List<Book> queryForPageItems(int begin , int pageSize);
}
