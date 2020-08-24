package com.sy.controller;

import com.github.pagehelper.PageInfo;
import com.sy.model.Inform;
import com.sy.model.Job;
import com.sy.model.base.Result;
import com.sy.service.InformService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/inform")
public class InformController {
    @Autowired
    InformService informService;
    private static Logger logger=Logger.getLogger(InformController.class);

/*
    @RequestMapping("/list.do")
    public Result selectAll(Inform inform)throws Exception{
        Result result=new Result();
        List<Inform> jobs = informService.selectAll();
        result.setCode(Result.SUCCESS_CODE);
        result.setMsg(Result.SUCCESS_MSG);
        logger.info("查询全部用户");
        result.setData(jobs);
        result.setCount(informService.count(inform));
        return result;
    }
*/



    @RequestMapping("/selectone.do")
    public Result select(Inform inform,int page,int limit)throws Exception{
        Result result=new Result();
        List<Inform> informs = informService.selectPage(inform, page, limit);
        PageInfo<Inform> of = PageInfo.of(informs);
        result.setCode(Result.SUCCESS_CODE);
        result.setMsg(Result.SUCCESS_MSG);
        result.setCount((int)of.getTotal());
        logger.info("查询某个用户");
        result.setData(informs);
        return result;
    }




    @RequestMapping("/update.do")
    public Result update(Inform inform)throws Exception{
        Result result=new Result();
        inform.setCreateDate(new Date());
        int update = informService.update(inform);
        if(update>0){
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("修改成功");
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
        int delete = informService.delete(id);
        if(delete>0){
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("删除成功");
            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }


    @RequestMapping("/insert.do")
    public Result insert(Inform inform)throws  Exception{
        Result result=new Result();
        inform.setCreateDate(new Date());
        int insert = informService.insert(inform);
        if(insert>0){
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("添加成功");
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
            int delete = informService.delete(ids[i]);
            if(delete>0){
                ++count;
            }

        }

        System.out.println(count);
        if(count==ids.length){
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
