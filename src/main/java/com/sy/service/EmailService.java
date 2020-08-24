package com.sy.service;

import com.sy.model.Department;
import com.sy.model.Document;
import com.sy.model.Email;
import com.sy.model.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmailService {
    List<Email> selectAll(Email employee)throws Exception;

    int delete(int id)throws Exception;
    int delete1(int toId)throws Exception;
    int insert(Email email)throws Exception;
    int count(Email email)throws Exception;
    List<Email> selectPage(Email email, int page, int limit)throws Exception;
}
