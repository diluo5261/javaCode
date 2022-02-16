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

    @Override
    public Integer queryForPageTotal() {
        String sql = "select count(id) from book";
         Number number = (Number) queryForSingleValue(sql);
         return number.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select id, name,price ,author,sales,stock,img_path from book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalByPrice(int min, int max) {
        String sql = "select count(id) from book where price between ? and ?";
        Number number = (Number) queryForSingleValue(sql,min,max);
        return number.intValue();

    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select id, name,price ,author,sales,stock,img_path from book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);


    }
}
