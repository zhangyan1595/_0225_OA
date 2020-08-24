package com.sy.service;

import com.github.pagehelper.PageHelper;
import com.sy.mapper.DeptMapper;
import com.sy.model.Account;
import com.sy.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;
    @Override
    public List<Department> selectAll() throws Exception {
        return deptMapper.selectAll();
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int update(Department department) throws Exception {
        return deptMapper.update(department);
    }

    @Override
    public List<Department> select(Department department) throws Exception {
        return deptMapper.select(department);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int insert(Department department) throws Exception {
        return deptMapper.insert(department);
    }


    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int delete(int id) throws Exception {
        return deptMapper.delete(id);
    }


    @Override
    public int count(Department department) throws Exception {
        return deptMapper.count(department);
    }

    @Override
    public List<Department> selectPage(Department departmentt, Integer page, Integer limit) throws Exception {

        if(page!=0&&limit!=0) {
            PageHelper.startPage(page, limit);
        }
        List<Department> departments = deptMapper.select(departmentt);
        return departments;

    }

}
