package com.dilo.test;

import com.dilo.domain.User;
import com.dilo.service.UserService;
import com.dilo.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(3,"lisi","lisi666","lisi@163.com"));
        userService.registerUser(new User(4,"wangwu","wangwu666","wangwu@qq.com"));

    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(0, "lisi", "lisi666", "555")));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("lisi")){
            System.out.println("用户名已存在");
        }else{
            System.out.println("用户名可用");
        }
    }
}