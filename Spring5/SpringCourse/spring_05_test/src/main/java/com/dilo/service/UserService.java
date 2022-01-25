package com.dilo.service;

import com.dilo.domain.User;

import java.util.List;

public interface UserService {
    public List<User> list();

    void save(User user, Long[] roleIds);

    void del(Long userId);
}
