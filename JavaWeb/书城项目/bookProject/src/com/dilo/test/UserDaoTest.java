package com.dilo.test;

import com.dilo.dao.impl.UserDao;
import com.dilo.dao.impl.UserDaoImple;
import com.dilo.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryByUsername() {
        UserDao userDao = new UserDaoImple();
        User user = userDao.queryByUsername("admin");
        System.out.println(user);

        if(user == null){
            System.out.println("用户名可用!");
        }else{
            System.out.println("用户名不可用!");
        }

    }

    @Test
    public void queryByUsernameAndPassword() {
        UserDao userDao = new UserDaoImple();
        if(userDao.queryByUsernameAndPassword("admin","admin") == null){
            System.out.println("用户名或密码错误");
        }else{
            System.out.println("查询成功!");
        }
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImple();
        System.out.println(userDao.saveUser(new User(2,"zhansan","zhansan","zhansan@163.com")));
    }
}