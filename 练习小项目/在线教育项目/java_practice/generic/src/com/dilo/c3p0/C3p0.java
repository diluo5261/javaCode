package com.dilo.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class C3p0 {

    @Test
    public void test() throws SQLException {
        ComboPooledDataSource myc3p0 = new ComboPooledDataSource("myc3p0");
        Connection conn = myc3p0.getConnection();
        System.out.println(conn!= null?"连接成功":"连接失败");

    }
}
