package com.dilo.service.impl;

import com.dilo.dao.impl.UserDao;
import com.dilo.dao.impl.UserDaoImple;
import com.dilo.domain.User;
import com.dilo.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImple();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    /**
     *
     * @param user
     * @return 如果返回null,说明登录失败,返回有值,是登录成功
     */
    @Override
    public User login(User user) {
        return userDao.queryByUsernameAndPassword(user.getUsername(),user.getPassword());
    }


    /**
     * 检查用户名使用可用
     * @param username
     * @return
     */
    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryByUsername(username) == null){
            //等于null,说明没查到,没查到表示可用
            return false;
        }

        return true;
    }
}
