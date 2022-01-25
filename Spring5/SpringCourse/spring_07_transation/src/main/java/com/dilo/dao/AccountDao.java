package com.dilo.dao;

import com.dilo.domain.Account;

import java.util.List;

public interface AccountDao {

    List<Account> findAll();

    int add(Account account);

    int update(Account account);

}
