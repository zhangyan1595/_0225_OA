package com.sy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sy.mapper.AccountMapper;
import com.sy.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
    /*增删改要加事务*/
    @Autowired
    AccountMapper accountMapper;
    @Override
    public Account findbyname(String loginname,String password) throws Exception {
        System.out.println(accountMapper+"111111");
        Account selectbyname = accountMapper.selectbyname(loginname, password);
        return selectbyname;
    }

    @Override
    public List<Account> selectAll() throws Exception {
        return  accountMapper.selectAll();
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public int delete(int id) throws Exception {
        return accountMapper.delete(id);
    }

    @Override
    public List<Account> select(Account account) throws Exception {
        return accountMapper.select(account);
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public int update(Account account) throws Exception {
        return accountMapper.update(account);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int insert(Account account) throws Exception {
        return accountMapper.insert(account);
    }

    @Override
    public int count(Account account) throws Exception {
        return accountMapper.count(account);
    }

    @Override
    public List<Account> selectPage(Account account, int page, int limit) throws Exception {
        PageHelper.startPage(page, limit);
        List<Account> accounts = accountMapper.select(account);
        return accounts;
    }
}
