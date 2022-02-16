package com.dilo.dao;

import com.dilo.domain.User;

public interface UserDao {


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 返回null,说明没有这个用户
     */
    public User queryByUsername(String username);


    /**
     * 根据用剧名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null , 说明用户名或者密码错误
     */
    public User queryByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);
}
