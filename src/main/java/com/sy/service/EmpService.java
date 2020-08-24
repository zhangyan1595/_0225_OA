package com.sy.service;

import com.sy.model.Document;
import com.sy.model.Email;
import com.sy.model.Employee;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface EmpService {
    List<Employee> selectAll()throws Exception;
    int insert(Employee employee)throws Exception;
    int update(Employee employee)throws Exception;
    List<Employee> select(Employee e)throws Exception;
    int delete(int id)throws Exception;
    List<Employee> selectPage(Employee employee, Integer page, Integer limit)throws Exception;

    int deleteuid(int uid)throws Exception;

    int deletedid(int did)throws Exception;

    int deletejid(int jid)throws Exception;
}
