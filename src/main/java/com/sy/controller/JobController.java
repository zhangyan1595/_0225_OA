package com.sy.controller;

import com.github.pagehelper.PageInfo;
import com.sy.model.Department;
import com.sy.model.Document;
import com.sy.model.Job;
import com.sy.model.base.Result;
import com.sy.service.EmpService;
import com.sy.service.JobService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    JobService jobService;
    @Autowired
    EmpService empService;

    private static Logger logger=Logger.getLogger(JobController.class);


    @RequestMapping("/update.do")
    public Result update(Job job)throws Exception{
        Result result=new Result();
        int update =jobService.update(job) ;
        if(update>0){
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("修改用户成功");
            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }


    @RequestMapping("/selectone.do")
    public Result selectone(Job department, Integer page, Integer limit)throws Exception{
        Result result=new Result();
        System.out.println("page"+limit);
        List<Job> jobs = jobService.selectPage(department, page, limit);
        PageInfo<Job> of = PageInfo.of(jobs);
        if(jobs!=null){
            result.setMsg(Result.SUCCESS_MSG);
            result.setCode(Result.SUCCESS_CODE);
            result.setData(jobs);
            logger.info("模糊查询成功");
            result.setCount((int)of.getTotal());
            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }



    @RequestMapping("/insert.do")
    public Result insert(Job department)throws Exception{

        Result result=new Result();
        int insert = jobService.insert(department);
        if(insert>0){
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("添加成功");
            result.setCode(Result.SUCCESS_CODE);
            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }




    @RequestMapping("/delete.do")
    public Result delete(int id)throws Exception{
        Result result=new Result();
        int delete = jobService.delete(id);
        if(delete>0){
            System.out.println("走了吗");
            result.setMsg(Result.SUCCESS_MSG);
            result.setCode(Result.SUCCESS_CODE);
            logger.info("删除成功");
            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }

    @RequestMapping("/deleteAll.do")
    public Result delete(int[] ids)throws Exception{
        Result result=new Result();
       int count=0;
        for (int i = 0; i <ids.length ; i++) {
            int deletedid = empService.deletejid(ids[i]);

                int delete = jobService.delete(ids[i]);
                if(delete>0){
                    ++count;
                }
        }
        if(count==ids.length){
            System.out.println("走了吗");
            result.setMsg(Result.SUCCESS_MSG);
            result.setCode(Result.SUCCESS_CODE);
            logger.info("批量删除成功");
            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }
}
