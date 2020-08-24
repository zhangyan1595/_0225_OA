package com.sy.service;

import com.github.pagehelper.PageHelper;
import com.sy.mapper.SignMapper;
import com.sy.model.SignIn;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SignServiceImpl implements SignService {
    @Autowired
    SignMapper signMapper;

    @Override
    public List<SignIn> select(SignIn signIn) throws Exception {
        return signMapper.select(signIn);
    }

    @Override
    public List<SignIn> selectone(Date begintime, Date endtime) throws Exception {
        return signMapper.selectone(begintime,endtime);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int insert(SignIn signIn) throws Exception {
        return signMapper.insert(signIn);
    }

    @Override
    public List<SignIn> selectPage(Date begintime, Date endtime, Integer page, Integer limit) throws Exception {
        if(page!=0&&limit!=0){
            PageHelper.startPage(page, limit);
        }
        return signMapper.selectone(begintime,endtime);
    }

    @Override
    public List<SignIn> selectPage1(SignIn signIn, Integer page, Integer limit) throws Exception {
        if(page!=0&&limit!=0){
            PageHelper.startPage(page, limit);
        }
        return signMapper.select(signIn);
    }

    @Override
    public List<SignIn> select1(SignIn signIn) throws Exception {
        return signMapper.select1(signIn);
    }
}
