package com.sy.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 如果表单需要做重复提交校验，那么开发者必须在表单中设置token,
 * 否则，就认定无需做表单重复提交校验
 */
public class FormFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response  = (HttpServletResponse)servletResponse;
        String method = request.getMethod();
        String token = request.getParameter("token");
        Object sessionToken = request.getSession().getAttribute("token");
        if(method.equals("POST")){
            //表单重复提交校验
            if(sessionToken!=null){
                Long sessionToken2 = (Long) sessionToken;
                if(token==null||!token.equals(sessionToken2.toString())){
                    response.sendRedirect("/error.jsp");
                }else{
                    //正常提交清空Token
                    request.getSession().removeAttribute("token");
                    filterChain.doFilter(request, response);
                }
            }else{
                if(token!=null){
                    response.sendRedirect("/error.jsp");
                }else{
                    filterChain.doFilter(request, response);
                }
            }
        }else{
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
