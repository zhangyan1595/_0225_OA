package com.sy.service;

import com.sy.model.Document;
import com.sy.model.Inform;
import com.sy.model.Job;

import java.util.List;

public interface JobService {
    List<Job> selectAll()throws Exception;
    int update(Job department)throws Exception;
    List<Job> select(Job department)throws Exception;
    int insert(Job department)throws Exception;
    int delete(int id)throws Exception;
    List<Job> selectPage(Job job,Integer page,Integer limit)throws Exception;
    Job selectPdf(int id)throws Exception;
}
