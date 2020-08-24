package com.sy.controller;

import com.github.pagehelper.PageInfo;
import com.sy.model.Department;
import com.sy.model.Employee;
import com.sy.model.Job;
import com.sy.model.base.Result;
import com.sy.service.DeptService;
import com.sy.service.EmpService;
import com.sy.service.JobService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Resource
    EmpService empService;

    private static Logger logger=Logger.getLogger(EmpController.class);

/*
    @RequestMapping("/list.do")
    public Result list(Employee employee)throws Exception{
        ArrayList list=new ArrayList();
        Result result=new Result();
        List<Employee> employees = empService.selectAll();
      */
/*  for (Employee e:employees) {
            int jid = e.getJid();
            Job job=new Job();
            job.setId(jid);
            List<Job> select = jobService.select(job);
            String name = select.get(0).getName();
            e.setJname(name);
            Department department=new Department();
            department.setId(e.getDid());
            List<Department> select1 = deptService.select(department);
            String name1 = select1.get(0).getName();
            e.setDname(name1);
            list.add(e);
        }
*//*

        result.setCount(empService.count(employee));
        result.setCode(Result.SUCCESS_CODE);
        result.setMsg(Result.SUCCESS_MSG);
        result.setData(employees);
        logger.info("查询列表成功");
        return result;

    }
*/



    @RequestMapping("/insert.do")
    public Result insert(Employee employee)throws Exception{
        Result result=new Result();
        employee.setCreateDate(new Date());
        int insert = empService.insert(employee);
        if(insert>0){
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("查询列表成功");
            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }

    }


    @RequestMapping("/update.do")
    public Result update(Employee employee)throws Exception{
        Result result=new Result();
        int insert = empService.update(employee);
        System.out.println(insert+"sss");
        if(insert>0){
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("修改列表成功");
            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }

    }




    @RequestMapping("/selectone.do")
    public Result selectone(Employee e,Integer page,Integer limit)throws Exception{
        Result result=new Result();
        List<Employee> employees = empService.selectPage(e, page, limit);
        PageInfo<Employee> of = PageInfo.of(employees);
        if(employees!=null){
            result.setMsg(Result.SUCCESS_MSG);
            result.setCode(Result.SUCCESS_CODE);
            result.setData(employees);
            result.setCount((int)of.getTotal());
            logger.info("模糊查询成功");
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
        int delete = empService.delete(id);
        if(delete>0){
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("删除列表成功");
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
            int delete = empService.delete(ids[i]);
            if(delete>0){
                ++count;
            }

        }
        if(count==ids.length){
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("批量删除成功");
            result.setCode(Result.SUCCESS_CODE);

            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }

}
