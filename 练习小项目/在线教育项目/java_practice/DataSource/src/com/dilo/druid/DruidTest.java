package com.dilo.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;
import org.omg.CORBA.portable.InputStream;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {

    @Test
    public void test() throws IOException {
        Properties pro = new Properties();


        pro.load(new FileInputStream("druid.properties"));


        Connection conn = null;


        try {
            DataSource source = DruidDataSourceFactory.createDataSource(pro);
            conn = source.getConnection();
            System.out.println("连接OK");

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
