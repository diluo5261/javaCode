package com.dilo.service.impl;

import com.dilo.dao.RoleDao;
import com.dilo.dao.UserDao;
import com.dilo.domain.Role;
import com.dilo.domain.User;
import com.dilo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<User> list() {
        List<User> userList = userDao.findAll();
        for (User user:userList) {
            //获取user的id
            Long id = user.getId();
            //将id作为数据,查询当前userId对应的Role集合
            List<Role> roles = roleDao.findRoleById(id);
            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        //1.向sys_user中存数据
        Long userId = userDao.save(user);
        //2.向sys_user_role中存储数据
        userDao.saveUserRoleRel(userId,roleIds);

    }

    @Override
    public void del(Long userId) {
        //1.删除关系表
        userDao.delUSerRoleRel(userId);
        //2.删除user表
        userDao.del(userId);


    }
}
