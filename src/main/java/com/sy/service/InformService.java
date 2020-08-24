package com.sy.service;

import com.sy.model.Employee;
import com.sy.model.Inform;

import java.util.List;

public interface InformService {
    List<Inform> select(Inform inform)throws Exception;
    List<Inform> selectAll()throws Exception;
    int update(Inform inform)throws Exception;
    int delete(int id)throws Exception;
    int insert(Inform inform)throws Exception;
    int count(Inform inform)throws Exception;
    List<Inform> selectPage(Inform inform, int page, int limit)throws Exception;
}
