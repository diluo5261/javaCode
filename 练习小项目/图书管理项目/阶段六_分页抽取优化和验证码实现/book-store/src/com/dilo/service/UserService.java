package com.dilo.service;

import com.dilo.domain.User;

public interface UserService {

    /**
     * 注册业务
     * @param user
     */
    public void registerUser(User user);


    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 价差用户名是否可用
     */

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在,返回flase表示用户名可用
     */

    public boolean existsUserName(String username);
}
