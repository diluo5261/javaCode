package com.dilo.dao.impl;

import com.dilo.dao.UserDao;
import com.dilo.domain.User;

public class UserDaoImple extends BaseDao implements UserDao {
    @Override
    public User queryByUsername(String username) {
        String sql = "select * from user where username = ?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user values(null,?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
