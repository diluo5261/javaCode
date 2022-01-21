package com.dilo.service.impl;

import com.dilo.dao.BookDao;
import com.dilo.domain.Book;
import com.dilo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class BookServiceImpl2 implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public boolean save(Book book) {
        return bookDao.insert(book) >0;
    }

    @Override
    public boolean update(Book book) {
        return bookDao.updateById(book) >0;
    }

    @Override
    public boolean delete(Integer id) {
        return bookDao.deleteById(id) > 0;

    }

    @Override
    public Book getById(Integer id) {
        return bookDao.selectById(id);
    }

    @Override
    public List<Book> getAll() {

        return bookDao.selectList(null);
    }
}
