package com.dilo.service.impl;

import com.dilo.dao.UserDao;
import com.dilo.dao.impl.UserDaoImpl;
import com.dilo.domain.User;
import com.dilo.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao  dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询

        return dao.findAll();
    }
}
