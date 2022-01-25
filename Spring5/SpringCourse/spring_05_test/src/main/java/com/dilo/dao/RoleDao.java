package com.dilo.dao;

import com.dilo.domain.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findAll();

    void save(Role role);


    List<Role> findRoleById(Long id);
}
