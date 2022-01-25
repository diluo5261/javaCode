package com.dilo;

import com.alibaba.druid.pool.DruidDataSource;
import com.dilo.dao.AccountDao;
import com.dilo.domain.Account;
import com.dilo.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MyTest {

    @Autowired
    private AccountService accountService;




    @Test
    public void test01(){
        accountService.findAll();
    }

    @Test
    public void addTest(){
        accountService.add();
    }

    @Test
    public void updateTest(){
        accountService.update();
        accountService.findAll();
    }
}
