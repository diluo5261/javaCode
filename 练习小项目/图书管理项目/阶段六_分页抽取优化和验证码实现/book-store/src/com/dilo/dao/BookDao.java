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

    Integer queryForPageTotalByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
