package com.dilo.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Test {

    @Test
    public void test(){
         ComboPooledDataSource cpds =new ComboPooledDataSource("c3p0");
        Connection conn = null;
        try {
            conn = cpds.getConnection();
            System.out.println("连接OK");
        } catch (SQLException e) {
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
