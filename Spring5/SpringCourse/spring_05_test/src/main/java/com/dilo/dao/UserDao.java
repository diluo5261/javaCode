package com.dilo.dao;

import com.dilo.domain.User;

import java.util.List;

public interface UserDao {

    public List<User> findAll();

    Long save(User user);

    void saveUserRoleRel(Long userId, Long[] roleIds);

    void delUSerRoleRel(Long userId);

    void del(Long userId);
}
