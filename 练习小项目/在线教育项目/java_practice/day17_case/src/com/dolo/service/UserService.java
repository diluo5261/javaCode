package com.dolo.service;

import com.dolo.domain.User;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    public List<User> findAll();
}
