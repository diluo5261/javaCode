package com.dilo;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.dilo.dao.BookDao;
import com.dilo.domainn.Book;
import com.dilo.util.DBUtil;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


public class BookDaoTest {
    @org.junit.Test
    public void returnBookTest(){
        BookDao bookDao = new BookDao();
        boolean b = bookDao.returnBookById(10);
        if (b){
            System.out.println("归还成功!");
            List<Book> bookList = bookDao.selectAll();
            for(Book book1 :bookList){
                System.out.println(book1);
            }
        }else{
            System.out.println("归还失败!");
        }
    }

    @org.junit.Test
    public void borrowBookTest(){
        BookDao bookDao = new BookDao();
        boolean b = bookDao.borrowBookById(10);

        if (b){
            System.out.println("借阅成功!");
            List<Book> bookList = bookDao.selectAll();
            for(Book book1 :bookList){
                System.out.println(book1);
            }
        }else{
            System.out.println("借阅失败!");
        }
    }

    @org.junit.Test
    public void delBookTest(){
        BookDao bookDao = new BookDao();
        boolean b = bookDao.delBookById(17);
        System.out.println(b);
    }

    @org.junit.Test
    public void addBookTest(){
        Book book = new Book();
        book.setName("驻京办主任");
        book.setAuthor("王晓方");
        book.setPrice(200000);
        book.setType("官场小说");

        BookDao bookDao = new BookDao();
        boolean add = bookDao.add(book);
        if (add){
            System.out.println(book.getName()+"添加成功");
            List<Book> bookList = bookDao.selectAll();
            for(Book book1 :bookList){
                System.out.println(book1);
            }
        }else{
            System.out.println("添加失败");
        }
    }


    @org.junit.Test
    public  void selectByNameTest(){
        BookDao bookDao = new BookDao();
        List<Book> bookList = bookDao.selectByName("西54");
        for(Book book :bookList){
            System.out.println(book);
        }
    }


    @org.junit.Test
    public void selectAllTest(){
        BookDao bookDao = new BookDao();
        List<Book> bookList = bookDao.selectAll();
        for(Book book :bookList){
            System.out.println(book);
        }
        System.out.println("查询完毕");
    }



    @org.junit.Test
    public void testConn() throws SQLException {
        Connection conn = DBUtil.getConnection();
        System.out.println(conn);
    }

    @org.junit.Test
    public void test2() throws Exception {
        Properties properties = new Properties();

        InputStream is = BookDaoTest.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(is);

        DataSource ds =  DruidDataSourceFactory.createDataSource(properties);
        Connection conn = ds.getConnection();
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
//        System.out.println(dataSource.getConnection());
    }
}
