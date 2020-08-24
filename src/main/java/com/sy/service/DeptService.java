package com.sy.service;

import com.sy.model.Account;
import com.sy.model.Department;

import java.util.List;

public interface DeptService {
    List<Department> selectAll()throws Exception;
    int update(Department department)throws Exception;
    List<Department> select(Department department)throws Exception;
    int insert(Department department)throws Exception;
    int count(Department department)throws Exception;
    int delete(int id)throws Exception;
    List<Department> selectPage(Department department, Integer page, Integer limit)throws Exception;

}
