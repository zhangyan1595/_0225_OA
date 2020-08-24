package com.sy.service;

import com.github.pagehelper.PageHelper;
import com.sy.mapper.JobMapper;
import com.sy.model.Document;
import com.sy.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobMapper jobMapper;

    @Override
    public List<Job> selectAll() throws Exception {
        return jobMapper.selectAll();
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int update(Job department) throws Exception {
        return jobMapper.update(department);
    }

    @Override
    public List<Job> select(Job department) throws Exception {
        return jobMapper.select(department);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int insert(Job department) throws Exception {
        return jobMapper.insert(department);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int delete(int id) throws Exception {
        return jobMapper.delete(id);
    }

    @Override
    public List<Job> selectPage(Job job, Integer page, Integer limit) throws Exception {
        System.out.println(job + "zou....");
        if (page != 0 && limit != 0) {
            PageHelper.startPage(page, limit);
        }
        return jobMapper.select(job);
    }

    @Override
    public Job selectPdf(int id) throws Exception {
        return jobMapper.selectPdf(id);
    }
}
