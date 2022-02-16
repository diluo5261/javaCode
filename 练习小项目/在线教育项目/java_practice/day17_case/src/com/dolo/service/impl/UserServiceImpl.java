package com.dolo.service.impl;

import com.dolo.dao.UserDao;
import com.dolo.dao.impl.UserDaoImpl;
import com.dolo.domain.User;
import com.dolo.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用DAO完成查询


        return dao.findAll();
    }
}
