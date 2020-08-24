package com.sy.service;

import com.github.pagehelper.PageHelper;
import com.sy.mapper.EmplMapper;
import com.sy.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmplMapper emplMapper;
    @Override
    public List<Employee> selectAll() throws Exception {
        return emplMapper.selectAll();
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int insert(Employee employee) throws Exception {
        return emplMapper.insert(employee);
    }


    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int update(Employee employee) throws Exception {
        return emplMapper.update(employee);
    }

    @Override
    public List<Employee> select(Employee e) throws Exception {
        return emplMapper.select(e);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int delete(int id) throws Exception {
        return emplMapper.delete(id);
    }

    @Override
    public List<Employee> selectPage(Employee employee, Integer page, Integer limit) throws Exception {
        PageHelper.startPage(page,limit);
        return emplMapper.select(employee);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int deleteuid(int uid) throws Exception {
        return emplMapper.deleteuid(uid);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int deletedid(int did) throws Exception {
        return emplMapper.deletedid(did);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int deletejid(int jid) throws Exception {
        return emplMapper.deletejid(jid);
    }
}
