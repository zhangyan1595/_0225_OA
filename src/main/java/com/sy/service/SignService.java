package com.sy.service;

import com.sy.model.Job;
import com.sy.model.SignIn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface SignService {
    List<SignIn> select(SignIn signIn)throws Exception;
    List<SignIn> selectone(Date begintime, Date endtime)throws Exception;
    int insert(SignIn signIn)throws Exception;

    List<SignIn> select1(SignIn signIn)throws Exception;

    List<SignIn> selectPage(Date begintime, Date endtime, Integer page, Integer limit)throws Exception;
    List<SignIn> selectPage1(SignIn signIn, Integer page, Integer limit)throws Exception;
}
