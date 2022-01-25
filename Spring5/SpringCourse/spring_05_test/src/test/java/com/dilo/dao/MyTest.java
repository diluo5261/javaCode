package com.dilo.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.dilo.domain.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class MyTest {

    //@Autowired
    //private RoleDao roleDao;

    @Test
    public void roleDaoTest(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");

        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(druidDataSource);
        List<Role> query = template.query("select * from sys_role", new BeanPropertyRowMapper<Role>(Role.class));
        System.out.println(query);




    }
}
