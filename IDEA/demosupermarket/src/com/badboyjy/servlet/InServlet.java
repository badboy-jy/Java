package com.badboyjy.servlet;

import com.badboyjy.bean.User;
import com.badboyjy.service.InService;
import com.badboyjy.service.impl.InServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@WebServlet(name = "InServlet",urlPatterns = "/In")
public class InServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1.接受参数
        String method = request.getParameter("method");

        try {
            //通过反射获取对象
            Class cla = this.getClass();
            //调取对象的方法
            Method method1 = cla.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method1.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void checkname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        //2.数据库查询
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        InService service = new InServiceImpl();
        User user = new User();
        user.setUserName(userName);
        user.setUserPass(null);
        User user1=service.login(user);
        if(user1.getUserPass()==null){
            //返回:不可用
            out.print("用户不存在，请检查或注册用户");
        }else{
            //可以用
            out.print("");
        }
    }
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userPass = request.getParameter("password");
        PrintWriter out = response.getWriter();
        //2.数据库查询
        response.setContentType("text/html;charset=utf-8");
        InService service = new InServiceImpl();
        User user = new User();
        user.setUserName(userName);
        user.setUserPass(userPass);
        User user1=service.login(user);
        request.getSession().setAttribute("userName",user1.getUserName());
       if (userName==null){
            out.write("<script>alert('您未登录');location.href='/login.jsp'</script>");
        }else {
           if (user1.getUserPass() != null) {
               request.getSession().setAttribute("quanxian", user1.getUserQuanXian());
               request.getSession().setAttribute("userid", user1.getUserId());
               request.getRequestDispatcher("/admin_index.jsp").forward(request, response);
               //返回:不可用
           } else {
               out.write("<script>alert('密码错误');location.href='/login.jsp'</script>");
               //可以用
           }
       }
    }
    protected void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("/login.jsp");
    }

}
