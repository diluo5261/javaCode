package com.dilo.service;

import com.dilo.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> list();


    void save(Role role);
}
