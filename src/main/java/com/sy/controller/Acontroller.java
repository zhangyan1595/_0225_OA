package com.sy.controller;

import com.github.pagehelper.PageInfo;
import com.sy.model.Account;
import com.sy.model.base.Result;
import com.sy.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/user")
public class Acontroller {
    @Autowired
    AccountService accountService;
    @Autowired
    EmailService emailService;
    @Autowired
    EmpService empService;
    @Autowired
    DocuService docuService;
    private static Logger logger = Logger.getLogger(Acontroller.class);

    @RequestMapping("/login.do")
    public Result dologin(String loginname, String password, String value, HttpSession session, HttpServletResponse httpServletResponse) throws Exception {
        Result result = new Result();
        Account findbyname = accountService.findbyname(loginname, password);
        if (findbyname != null) {
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            /*将用户信息存进session*/
            session.setAttribute("sessionUser", findbyname);
            /*设置cooke值*/
            if (value.equals("1")) {
                System.out.println("wojinlaile");
                Cookie cookie = new Cookie("username", loginname);
                cookie.setMaxAge(24*3600);
                httpServletResponse.addCookie(cookie);

            }else {
                Cookie cookie = new Cookie("username", null);

                System.out.println("womeijinlai");
            }
            System.out.println(findbyname.getUsername() + "00000");
            result.setData(findbyname);
            logger.info("登陆成功");
            return result;
        } else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }

    }

    @RequestMapping("/session.do")
    public Result getSessiion(HttpServletRequest httpServletRequest) {
        Result result = new Result();
        Account user = (Account) httpServletRequest.getSession().getAttribute("sessionUser");
        result.setData(user);
        return result;

    }

    @RequestMapping("/list.do")
    public Result userlist(Account account, int page, int limit) throws Exception {
        List<Account> accounts = accountService.selectPage(account, page, limit);
        long total = PageInfo.of(accounts).getTotal();
        Result result = new Result();
        if(accounts.isEmpty()){
            result.setCode(Result.FailED_CODE);
            return result;
        }else {
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            result.setCount((int) total);
            logger.info("查询用户列表");
            result.setData(accounts);
            return result;
        }

    }

    @RequestMapping("/deleteAll.do")
    public Result deleteAll(int[] ids) throws Exception {
        Result result = new Result();
        int count = 0;
        for (int i = 0; i < ids.length; i++) {
             int i1 = emailService.delete1(ids[i]);
             int deleteuid = empService.deleteuid(ids[i]);
             int i2 = docuService.delete1(ids[i]);
                int delete = accountService.delete(ids[i]);
                if (delete > 0) {
                    ++count;
            }
        }
        if (count == ids.length) {
            result.setMsg(Result.SUCCESS_MSG);
            result.setCode(Result.SUCCESS_CODE);
            logger.info("批量删除成功");
            return result;
        } else {
            logger.info("业务异常");
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            return result;
        }
    }

    @RequestMapping("/update.do")
    public Result update(Account account) throws Exception {
        Result result = new Result();
        int update = accountService.update(account);
        if (update > 0) {
            result.setMsg(Result.SUCCESS_MSG);
            result.setCode(Result.SUCCESS_CODE);
            logger.info("修改成功");
            return result;
        } else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }

    @RequestMapping("/insert.do")
    public Result insert(Account account) throws Exception {
        Result result = new Result();
        account.setCreateDate(new Date());
        int insert = accountService.insert(account);
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


    @RequestMapping("/cookie.do")
    public Result getCookie(HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        Result result=new Result();
        for (Cookie c:cookies) {
            if(c.getName().equals("username")){
                result.setData(c.getValue());
            }
        }
       return result;
    }


    @RequestMapping("/removeSession.do")
    public Result remove(HttpSession httpSession){
        Result result=new Result();
        httpSession.removeAttribute("sessionUser");
        result.setCode(Result.SUCCESS_CODE);
        return result;
    }


}
