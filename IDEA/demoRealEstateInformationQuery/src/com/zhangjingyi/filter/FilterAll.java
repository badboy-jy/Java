package com.zhangjingyi.filter;

import com.zhangjingyi.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class FilterAll implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        //获得请求路径
        //获得请求路径
        String requestURI = request.getRequestURI();

        if(requestURI.endsWith("show.jsp")|| requestURI.endsWith("/findall")){
            User users = (User) request.getSession().getAttribute("usernow");
            //2.判断
            if(users==null){
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('请先登录');location.href='index.jsp'</script>");
            }else{
                //放行
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}

