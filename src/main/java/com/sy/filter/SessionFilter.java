package com.sy.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.login.jsp,login.do,orderslist.do
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response  = (HttpServletResponse)servletResponse;
        String requestURI = request.getRequestURI();
        if(requestURI.contains("/login.jsp")||
                requestURI.contains("/user/login")||
                requestURI.contains("/orders/orderslist")||
                requestURI.contains("/error.jsp")){
            filterChain.doFilter(request, response);
        }else{
            Object sessionUser = request.getSession().getAttribute("sessionUser");
            if(sessionUser !=null){
                filterChain.doFilter(request, response);
            }else{
                response.sendRedirect("/login.jsp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
