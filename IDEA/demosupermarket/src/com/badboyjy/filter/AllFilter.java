package com.badboyjy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class AllFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        //获得请求路径
        String requestURI = request.getRequestURI();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        if (requestURI.contains("/bill") || requestURI.contains("/supplier") || requestURI.contains("/userAdd.jsp")) {

            Object quanxian = request.getSession().getAttribute("quanxian");

            if (quanxian == null) {
                out.print("<script>alert('异常错误');window.top.location=\"index.jsp\"</script>");
            } else {
                int qx = (int) quanxian;
                if (qx == 0) {
                    out.print("<script>alert('您不是管理员，无法跳转');location.href='/user?method=selectalluser'</script>");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
