package com.sy.controller;

import com.github.pagehelper.PageInfo;
import com.sy.model.SignIn;
import com.sy.model.base.Result;
import com.sy.service.SignService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sign")
public class SignController {
    @Autowired
    SignService signService;

    private static Logger logger = Logger.getLogger(SignController.class);

    @RequestMapping("/list.do")
    public Result select(SignIn signIn,Integer page,Integer limit) throws Exception {
        Result result = new Result();
        List<SignIn> signIns = signService.selectPage1(signIn, page, limit);
        PageInfo<SignIn> of = PageInfo.of(signIns);
        result.setData(signIns);
        result.setCount((int)of.getTotal());
        result.setCode(Result.SUCCESS_CODE);
        logger.info("查询成功");
        return result;

    }

    @RequestMapping("/selectone.do")
    public Result selectone(String begintime, String endtime, SignIn signIn,Integer page,Integer limit) throws Exception {
        Result result = new Result();
        Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(begintime);
        Date parse1 = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
        List<SignIn> signIns = signService.selectPage(parse, parse1, page, limit);
        PageInfo<SignIn> of = PageInfo.of(signIns);
        result.setCount((int)of.getTotal());
        result.setData(signIns);
        result.setCode(Result.SUCCESS_CODE);
        logger.info("单个查询成功");
        return result;
    }


    @RequestMapping("/insert.do")
    public Result insert(SignIn signIn) throws Exception {
        String[] s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).split(" ");
        String[] split = s[1].split(":");
        System.out.println(split[0]);
        if (Integer.parseInt(split[0].toString()) < 9 || Integer.parseInt(split[0].toString()) > 17) {
            signIn.setFlag(1);
        } else {
            signIn.setFlag(2);
        }
        Result result = new Result();
        int insert = signService.insert(signIn);
        if (insert > 0) {
            result.setCode(Result.SUCCESS_CODE);
            logger.info("添加成功");
            result.setData(signIn);
            return result;
        } else {
            result.setCode(Result.FailED_CODE);
            logger.info("业务异常");
            return result;
        }
    }

  /*  生成图表*/
    @RequestMapping("/list1.do")
    public Result select1(SignIn signIn) throws Exception {
        System.out.println(signIn.getCreatetime() );
        Result result = new Result();
        List<SignIn> select = signService.select1(signIn);
        result.setData(select);
        result.setCode(Result.SUCCESS_CODE);
        logger.info("查询成功");
        return result;
    }

}
