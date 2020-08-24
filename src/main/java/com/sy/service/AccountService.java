package com.sy.service;

import com.sy.model.Account;

import java.util.List;

public interface AccountService {
    Account findbyname(String loginname,String password)throws Exception;
    List<Account> selectAll()throws Exception;
    int delete(int id)throws Exception;
    List<Account> select(Account account)throws Exception;
    int update(Account account)throws Exception;
    int insert(Account account)throws Exception;
    int count(Account account)throws Exception;

    List<Account> selectPage(Account account,int page,int limit)throws Exception;
}
