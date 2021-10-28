package com.dilo.test;

import com.dilo.domain.User;
import com.dilo.service.UserService;
import com.dilo.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void registerUser() {
        UserService userService = new UserServiceImpl();
        userService.registerUser(new User(null,"hello","hello1","hello@163.com"));
    }

    @Test
    public void login() {
        UserService userService = new UserServiceImpl();
        User login = userService.login(new User(null, "hello", "hello1", "hello@163.com"));
        if (login == null){
            System.out.println("登录失败");
        }else{
            System.out.println(login
            );
            System.out.println("登录成功!");
        }
    }

    @Test
    public void existsUserName() {
        UserService userService = new UserServiceImpl();
        if (userService.existsUserName("tom")){
            System.out.println("用户名已存在!");
        }else{
            System.out.println("用户名可用!");
        }
    }
}