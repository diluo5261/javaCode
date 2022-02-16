package com.dilo.dao;


import com.dilo.domain.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username  用户名
     * @return 返回null说明没有这个用户信息
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param name
     * @param password
     * @return      返回null 说明用户名或密码错误
     */
    public User queryUserByUsernameAndPassword(String name,String password);

    public int saveUser(User user);
}
