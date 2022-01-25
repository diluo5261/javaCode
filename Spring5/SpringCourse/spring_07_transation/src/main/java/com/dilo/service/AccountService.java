package com.dilo.service;

import com.dilo.dao.AccountDao;
import com.dilo.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public void findAll(){
        List<Account> accountList = accountDao.findAll();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    public void add(){
        Account account = new Account();
        account.setName("哈哈");
        account.setBalance(500);
        int res = accountDao.add(account);

        System.out.println(res >0?"插入成功!":"插入失败!");
    }

    //@Transactional
    public void update(){
        Account account = new Account(2,"李四",1500);

        Account account1 = new Account(3,"王五",3500);

        int res1 = accountDao.update(account);
        //int i = 10/0;
        int res2 = accountDao.update(account1);

        if (res1 >0 && res2 >0){
            System.out.println("更改成功!");
        }else{
            System.out.println("更改失败!");
        }

    }
}
