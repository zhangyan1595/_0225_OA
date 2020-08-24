package com.sy.controller;


import com.github.pagehelper.PageInfo;
import com.sy.model.Email;
import com.sy.model.base.Result;
import com.sy.service.EmailService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;
    private static Logger logger = Logger.getLogger(EmailController.class);
    @RequestMapping("/list.do")
    public Result list(Email email,int page,int limit) throws Exception {
        Result result = new Result();
        List<Email> emails = emailService.selectPage(email, page, limit);
        PageInfo<Email> of = PageInfo.of(emails);
        result.setData(emails);
        result.setCount((int)of.getTotal());
        result.setCount(emailService.count(email));
        result.setCode(Result.SUCCESS_CODE);
        result.setMsg(Result.SUCCESS_MSG);
        logger.info("查询全部邮件");
        return result;
    }

    @RequestMapping("/delete.do")
    public Result delete(int id) throws Exception {
        Result result = new Result();
        int delete = emailService.delete(id);
        if (delete > 0) {
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("删除邮件成功");
            return result;
        } else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }


@RequestMapping("/insert.do")

    public Result insert(Email email, @RequestParam("file")MultipartFile file)throws Exception{
        Result result = new Result();
        email.setCreatetime(new Date());
    System.out.println(new Date());

        email.setAttachment(file.getOriginalFilename());
        int insert = emailService.insert(email);
        if(insert>0){
            file.transferTo(new File("C:\\Users\\Administrator.USER-20190508YF\\Desktop\\SHC\\_0225_OA\\src\\main\\webapp\\js\\upload",file.getOriginalFilename()));
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("添加邮件成功");
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
        int count=0;
        Result result=new Result();

        for (int i = 0; i <ids.length ; i++) {
            int delete = emailService.delete(ids[i]);
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
            logger.info("业务异常");
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);

            return result;
        }
    }


}
