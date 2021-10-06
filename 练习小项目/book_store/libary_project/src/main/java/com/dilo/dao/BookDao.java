package com.dilo.dao;

import com.dilo.domainn.Book;
import com.dilo.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    //1.新增书籍

    public boolean add(Book book) {

        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into book values(null,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, book.getName());
            pst.setString(2, book.getAuthor());
            pst.setInt(3, book.getPrice());
            pst.setString(4, book.getType());
            pst.setInt(5, book.isBorrowed() ? 1 : 0);

            //执行sql
            int ret = pst.executeUpdate();
            if (ret != 1) {
                //插入失败,返回false
                return false;
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pst, null);
        }
        return false;
    }

    //2.查看所有书籍
    //获取到数据库表中所有书籍记录
    public List<Book> selectAll(){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            //1. 获取连接
            conn = DBUtil.getConnection();

            //2.执行sql语句
            String sql = "select * from book";
            statement = conn.prepareStatement(sql);

            rs = statement.executeQuery();
            //遍历集合

            List<Book> list = new ArrayList<>();
            while(rs.next()){
                Book book = new Book();

                book.setBookId(rs.getInt("bookid"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getInt("price"));
                book.setBorrowed(rs.getInt("isBorrowed")== 1);
                list.add(book);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,statement,rs);
        }

        return null;
    }


    //3.根据名字查找书籍

    public List<Book> selectByName(String name){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            //获取连接
            conn = DBUtil.getConnection();

            //执行sql语句
            String sql = "select * from book where name = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1,name);

            rs = statement.executeQuery();

            List<Book> list = new ArrayList<>();
            while(rs.next()){
                Book book = new Book();

                book.setBookId(rs.getInt("bookid"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getInt("price"));
                book.setBorrowed(rs.getInt("isBorrowed")== 1);
                list.add(book);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,statement,rs);
        }

        return null;
    }

    //4.删除书籍
    public boolean delBookById(int bookId){


        Connection conn = null;
        PreparedStatement statement = null;
        try {

            //获取连接
            conn = DBUtil.getConnection();

            //执行sql语句
            String sql ="delete from book where bookid = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1,bookId);

            int ret = statement.executeUpdate();

            if (ret != 0){
                return true;
            }

            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,statement,null);
        }

        return false;

    }

    //5.借书
    /*
    存在问题:存在内存泄漏
     */
    public boolean borrowBookById(Integer bookId){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            //1. 获取连接
            conn=DBUtil.getConnection();

            //2.执行sql语句,查询相应书籍是否存在,或已被借出
            String sql = "select * from book where bookid = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1,bookId);
            //3.获取查询结果,
            rs = statement.executeQuery();

            if (rs.next()){
                //查询结果不为空,该编号书籍存在
                if(rs.getInt("isBorrowed") == 1){
                    System.out.println("编号为"+bookId+"的书籍已经借出");
                    return false;
                }
            }else{

                //查询结果为空,该编号书籍不能存在
                System.out.println("编号为"+bookId+"的书籍不存在!");
                return false;
            }

            //如果该编号的书籍存在,且未被借出
            //执行sql语句
            sql = "update book set isBorrowed = 1 where bookid =?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1,bookId);

            int ret = statement.executeUpdate();
            if (ret == 1){
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,statement,rs);
        }
        return false;
    }

    //6.还书
    public boolean returnBookById(Integer bookId){
        Connection conn = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        ResultSet rs= null;


        try {
            //1.获取数据库连接
            conn = DBUtil.getConnection();

            //执行sql语句,判断书籍是否存在,或已被借出
            String sql = "select * from book where bookid = ?";

            statement1 = conn.prepareStatement(sql);
            statement1.setInt(1,bookId);

            rs = statement1.executeQuery();

            if(rs.next()){
                if (rs.getInt("isBorrowed") ==0){
                    System.out.println("书籍未借出");
                    return false;
                }
            }else {
                //要查询的书籍不存在
                System.out.println("编号为:" + bookId+"的书籍不存在");
                return false;

            }

            sql = "update book set isBorrowed = 0 where bookid = ?";

            statement2 = conn.prepareStatement(sql);
            statement2.setInt(1,bookId);

            int ret = statement2.executeUpdate();

            if (ret != 0){
                return true;
            }

            return  false;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,statement1,rs);
            DBUtil.close(null,statement2,null);
        }
        return false;

    }
}
