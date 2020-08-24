package com.sy.controller;

import com.github.pagehelper.PageInfo;
import com.sy.model.Account;
import com.sy.model.Department;
import com.sy.model.base.Result;
import com.sy.service.DeptService;
import com.sy.service.EmailService;
import com.sy.service.EmpService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class Dcontroller {
    @Autowired
    DeptService deptService;
    @Autowired
    EmpService empService;

    private static Logger logger = Logger.getLogger(Dcontroller.class);

/*    @RequestMapping("/list.do")
    public Result selectAll()throws Exception{
        Result result=new Result();
        List<Department> accounts = deptService.selectAll();

        result.setCode(Result.SUCCESS_CODE);
        result.setMsg(Result.SUCCESS_MSG);
        logger.info("查询用户列表");
        result.setData(accounts);
        return result;
    }*/

    @RequestMapping("/update.do")
    public Result update(Department department) throws Exception {
        Result result = new Result();
        int update = deptService.update(department);
        if (update > 0) {
            result.setCode(Result.SUCCESS_CODE);
            logger.info("修改用户成功");
            result.setMsg(Result.SUCCESS_MSG);

            return result;
        } else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }


    @RequestMapping("/selectone.do")
    public Result selectone(Department department, Integer page, Integer limit) throws Exception {
        System.out.println(department.getName());
        Result result = new Result();
        List<Department> departments = deptService.selectPage(department, page, limit);
        PageInfo<Department> of = PageInfo.of(departments);
        long total = of.getTotal();
        if (departments != null) {
            result.setMsg(Result.SUCCESS_MSG);
            result.setCode(Result.SUCCESS_CODE);
            result.setCount((int) total);
            result.setData(departments);
            logger.info("模糊查询成功");
            return result;
        } else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }


    @RequestMapping("/insert.do")
    public Result insert(Department department) throws Exception {
        Result result = new Result();
        int insert = deptService.insert(department);
        if (insert > 0) {
            result.setMsg(Result.SUCCESS_MSG);
            result.setCode(Result.SUCCESS_CODE);
            logger.info("添加成功");
            return result;
        } else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }


    @RequestMapping("/delete.do")
    public Result delete(int id) throws Exception {
        Result result = new Result();
        int delete = deptService.delete(id);
        if (delete > 0) {
            System.out.println("走了吗");
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("删除成功");
            result.setCode(Result.SUCCESS_CODE);
            return result;
        } else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }

@RequestMapping("/deleteAll.do")
    public Result deleteAll(int[] ids) throws Exception {
        Result result = new Result();
        int count = 0;
        for (int i = 0; i < ids.length; i++) {
            int i1 = empService.deletedid(ids[i]);
                int delete = deptService.delete(ids[i]);
                if(delete>0){
                    ++count;
                }
        }
    System.out.println(count);
        if (count == ids.length) {
            logger.info("批量删除成功");
            result.setMsg(Result.SUCCESS_MSG);
            result.setCode(Result.SUCCESS_CODE);
            return result;
        } else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }

}
