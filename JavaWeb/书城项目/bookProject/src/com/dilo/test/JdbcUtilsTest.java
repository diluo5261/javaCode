package com.dilo.test;

import com.dilo.utils.JDBCUtils;
import org.junit.Test;

import java.sql.SQLException;

public class JdbcUtilsTest {
    @Test
    public void jdbcTest() throws SQLException {
        System.out.println(JDBCUtils.getConnection());

    }
}
