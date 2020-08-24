package com.sy.service;

import com.github.pagehelper.PageHelper;
import com.sy.mapper.EmailMapper;
import com.sy.mapper.EmplMapper;
import com.sy.model.Email;
import com.sy.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmailServiceImpl implements EmailService {
    @Autowired
    EmailMapper emailMapper;
    @Override
    public List<Email> selectAll(Email employee) throws Exception {
        return emailMapper.selectAll(employee);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int delete(int id) throws Exception {
        return emailMapper.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int insert(Email email) throws Exception {
        return emailMapper.insert(email);
    }

    @Override
    public int count(Email email) throws Exception {
        return emailMapper.count(email);
    }

    @Override
    public List<Email> selectPage(Email email, int page, int limit) throws Exception {
        if(page!=0&&limit!=0){
            PageHelper.startPage(page,limit);
        }
        return emailMapper.selectAll(email);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int delete1(int toId) throws Exception {
        return emailMapper.delete1(toId);
    }
}
