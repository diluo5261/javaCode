package com.dilo.service.impl;

import com.dilo.dao.Impl.UserDaoImpl;
import com.dilo.dao.UserDao;
import com.dilo.domain.User;
import com.dilo.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);


    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());

    }

    @Override
    public boolean existsUserName(String username) {
        if (userDao.queryUserByUsername(username) == null){
            System.out.println("用户名可用!");
            return false;

        }
        return true;
    }
}
