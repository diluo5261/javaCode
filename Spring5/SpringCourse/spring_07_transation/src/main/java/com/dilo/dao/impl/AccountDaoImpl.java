package com.dilo.dao.impl;

import com.dilo.dao.AccountDao;
import com.dilo.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;


@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        List<Account> list = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<>(Account.class));
        return list;
    }

    @Override
    public int add(Account account) {
        int res = jdbcTemplate.update("insert into account values(?,?,?)", null, account.getName(), account.getBalance());
        return res;
    }

    @Override
    public int update(Account account) {
        return jdbcTemplate.update("update account set balance = ? where id =?",account.getBalance(),account.getId());

    }

}
