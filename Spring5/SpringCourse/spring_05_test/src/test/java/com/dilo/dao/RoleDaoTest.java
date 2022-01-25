package com.dilo.dao;

import com.dilo.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class RoleDaoTest {

    @Resource
    private RoleDao roleDao;

    @Autowired
    private RoleService roleService;

    @Test
    public void findAll() {
        System.out.println(roleDao.findAll());
    }

    @Test
    public void serviceTest(){
        System.out.println(roleService.list());

    }
}