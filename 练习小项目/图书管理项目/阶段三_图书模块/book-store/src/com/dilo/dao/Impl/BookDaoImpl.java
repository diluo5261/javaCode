package com.dilo.dao.Impl;

import com.dilo.dao.BookDao;
import com.dilo.domain.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into book (name,price,author,sales,stock,img_path) values(?,?,?,?,?,?)";

        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from book where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update book set name =? ,price=?,author=?,sales=?,stock=?,img_path=? where id = ?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id, name,price ,author,sales,stock,img_path  from book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id, name,price ,author,sales,stock,img_path from book";
        return queryForList(Book.class,sql);
    }
}
