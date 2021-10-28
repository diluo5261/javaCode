package com.dilo.test;

import com.dilo.dao.Impl.UserDaoImpl;
import com.dilo.dao.UserDao;
import com.dilo.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByUsername("tom");
        System.out.println(user);
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByUsernameAndPassword("tom", "tom");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        int i = userDao.saveUser(new User(null, "tom11", "fdas", "fad"));
        if (i != -1){
            System.out.println("插入成功!");
        }else{
            System.out.println("插入失败!");
        }
    }
}