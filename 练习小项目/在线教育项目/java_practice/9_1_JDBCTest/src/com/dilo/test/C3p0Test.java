package com.dilo.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class C3p0Test {
    //方式一:相关参数,在程序中指定user,url,password
    @Test
    public void test1() throws IOException, PropertyVetoException, SQLException {
        //1. 创建一个数据源对象
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        //2. 通过配置文件mysql.properties 获取相关连接信息
        Properties ps = new Properties();
        ps.load(new FileInputStream("mysql.properties"));

        String user =ps.getProperty("user");
        String password = ps.getProperty("password");
        String url = ps.getProperty("url");
        String driver = ps.getProperty("driver");

        //给数据源 comboPooledDataSource
        //注意连接是由comboPooledDataSource 来管理
        cpds.setDriverClass(driver);
        cpds.setJdbcUrl(url);
        cpds.setUser(user);
        cpds.setPassword(password);

        //设置初始化连接数
        cpds.setInitialPoolSize(10);
        //最大连接数
        cpds.setMaxPoolSize(50);

        Connection conn = cpds.getConnection();
        System.out.println("连接OK!");
        conn.close();


    }

    @Test
    public void test2() throws SQLException {
        //方式二:使用配置文件的方式,
        ComboPooledDataSource cs = new ComboPooledDataSource("helloc3p0");
        Connection conn = cs.getConnection();
        System.out.println(conn);
        System.out.println("连接OK");

    }

    @Test
    public void test3() throws SQLException {
        ComboPooledDataSource cp = new ComboPooledDataSource("helloc3p0");
        Connection conn = cp.getConnection();
        System.out.println(conn);
    }
}
