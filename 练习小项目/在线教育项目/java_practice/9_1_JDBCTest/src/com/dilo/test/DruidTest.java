package com.dilo.test;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {

    @Test
    public void test1() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));

        //创建一个指定参数的数据库连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection conn = dataSource.getConnection();
        System.out.println("连接成功" + conn);
        conn.close();
    }

}
