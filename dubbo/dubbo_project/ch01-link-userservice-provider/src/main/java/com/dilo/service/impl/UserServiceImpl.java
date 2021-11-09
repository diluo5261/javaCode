package com.dilo.service.impl;

import com.dilo.mdel.User;
import com.dilo.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User queryUserById(Integer id) {
        User user = new User();
        user.setUsername("tom");
        user.setAge(15);
        user.setId(1);
        return user;
    }
}
