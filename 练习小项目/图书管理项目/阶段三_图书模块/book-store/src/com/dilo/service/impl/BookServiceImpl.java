package com.dilo.service.impl;

import com.dilo.dao.BookDao;
import com.dilo.dao.Impl.BookDaoImpl;
import com.dilo.domain.Book;
import com.dilo.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void add(Book book) {
        bookDao.addBook(book);

    }

    @Override
    public void deleteBookBuId(Integer id) {
bookDao.deleteById(id);
    }

    @Override
    public void updateBook(Book book) {
bookDao.updateBook(book);
    }

    @Override
    public Book queryBookBuId(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
}
