/*



import com.sy.model.Account;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 */
package com.sy.interceptor;

import com.sy.model.Account;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    /**
     * 请求到达控制器之前要执行的业务代码
     * 返回值为false,则执行请求的拦截.
     * 返回值为true,则表示不拦截请求,会向下调用后续的拦截器及控制器
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String url = request.getRequestURI().toString();
        System.out.println(url);// http://localhost:8080/springmvc_day04/user/order.do


        //解析字符串
        boolean flag2 = url.endsWith("/user/login.do");
        boolean flag3 = url.endsWith("/loginForm.html");
        boolean flag4 = url.endsWith("/pdf/pdf.do");
        boolean flag5 = url.endsWith("/uploadExcel/exportexcel.do");
        if (flag2) {
            return true;
        }
        Account sessionUser = (Account) request.getSession().getAttribute("sessionUser");

        if (sessionUser != null) {
            return true;
        }

        if (flag3) {
            return true;
        }
        if (flag4) {
            return true;
        }
        if (flag5) {
            return true;
        }
        if (request.getRequestURI().contains("cookie.do")){
            System.out.println("放行了吗");
            return true;
        }
        System.out.println("没放行");

        response.sendRedirect("/html/loginForm.html");
        return false;

    }


    /**
     * 表示控制器执行完后,将要执行的业务代码
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandle");
    }


    /**
     * 表示控制器执行完后,作出响应,将要执行的代码,在postHandle之后执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterCompletion");

    }
}

