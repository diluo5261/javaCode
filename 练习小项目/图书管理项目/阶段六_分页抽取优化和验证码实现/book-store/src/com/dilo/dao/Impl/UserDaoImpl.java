package com.dilo.dao.Impl;

import com.dilo.dao.UserDao;
import com.dilo.domain.User;


public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql ="select id,username,password,email from user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String name, String password) {
        String sql ="select id,username,password,email from user where username = ? and password = ?";
        return queryForOne(User.class,sql,name,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user values(?,?,?,?)";
        return update(sql,user.getId(),user.getUsername(),user.getPassword(),user.getEmail());
    }
}
