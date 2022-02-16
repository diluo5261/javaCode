package com.dilo.dao.impl;

import com.dilo.dao.BookDao;
import com.dilo.domain.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql ="insert into book values(null,?,?,?,?,?,?)";
        return this.update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from book where id =?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql ="update book set name =?,author=?,price=?,sales=?,stock=?,img_path=? where id =?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql ="select * from book where id =?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql ="select * from book";

        return queryForList(Book.class,sql);
    }
}
