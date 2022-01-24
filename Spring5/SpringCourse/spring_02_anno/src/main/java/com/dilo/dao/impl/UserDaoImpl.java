package com.dilo.dao.impl;

import com.dilo.dao.UserDao;

public class UserDaoImpl implements UserDao {



    public void init(){
        System.out.println("初始化方法");
    }

    public void destroy(){
        System.out.println("销毁方法!");


    }

    @Override
    public void save() {
        System.out.println("UserDao save method running...");
    }
}
