package com.dolo.dao.impl;

import com.dolo.dao.UserDao;
import com.dolo.domain.User;
import com.dolo.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        //执行sql语句
        String sql = "select * from user";
        List<User> query = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return query;
    }
}
