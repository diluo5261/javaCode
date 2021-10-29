package com.dilo.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    private static DruidDataSource dataSource;

    static {

        InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static Connection getConnection() throws SQLException {

        Connection conn = conns.get();
        try{
            if (conn == null){
                conn = dataSource.getConnection(); //从数据库连接池中获取连接
                conn.setAutoCommit(false); //设位置手动管理事务
                conns.set(conn);        //保存到ThreadLocal对象中，供后面的jdbc操作使用
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    /*
     * 提交事务，并释放连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();

        if (connection != null){//如果不等于 null，说明之前使用过连接，操作过数据库
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //一定要执行remove操作，否则会报错
        conns.remove();

    }

    public static void rollbackAndClose(){
        Connection connection = conns.get();

        if (connection != null){//如果不等于 null，说明之前使用过连接，操作过数据库
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        conns.remove();
    }


   /* public static void close(Connection connection){

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }*/
}
