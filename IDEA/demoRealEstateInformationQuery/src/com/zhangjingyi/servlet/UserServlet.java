package com.zhangjingyi.servlet;

import com.zhangjingyi.bean.User;
import com.zhangjingyi.service.UserService;
import com.zhangjingyi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1.接受参数
        String method = request.getParameter("method");
        if (method == null || ("").equals(method)) {
            method = "login";
        }
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
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardId = request.getParameter("cardId");
        String password = request.getParameter("password");
        User user = new User();
        user.setCardId(cardId);
        user.setPassword(password);
        UserService userService = new UserServiceImpl();
        User user1= userService.login(user);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (user1!=null&&user1.getCardId()!=null&&user1.getStatus()==1) {
            request.getSession().setAttribute("usernow",user1);
            out.print("<script>alert('登录成功');location.href='/show.jsp'</script>");
        }if (user1!=null&&user1.getCardId()!=null&&user1.getStatus()==0){
            out.print("<script>alert('该账号已被冻结');location.href='/login.jsp'</script>");
        }else{
            out.print("<script>alert('密码或身份证号错误');location.href='/login.jsp'</script>");

        }
    }
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardId = request.getParameter("cardId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        Long sexid =Long.parseLong(cardId);
        int sex=0;
        if (sexid%2==0){
        sex=0;
        }else {
            sex=1;
        }
        User user = new User();
        user.setCardId(cardId);
        user.setPassword(password);
        user.setName(name);
        user.setSex(sex);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(new Date());

            user.setLastModifyTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserService userService = new UserServiceImpl();
        int i= userService.regist(user);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (i>0){
            out.print("<script>alert('注册成功');location.href='/login.jsp'</script>");
        }else {
            response.sendRedirect("/regist.jsp");
        }


    }


    protected void tuichu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("/index.jsp");
    }
    protected void checkcardId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cardId = request.getParameter("cardId");
        //2.数据库查询
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        UserService service = new UserServiceImpl();
        User user = new User();
        user.setCardId(cardId);
        user.setPassword(null);
        User user1=service.login(user);
        if(user1!=null&&user1.getCardId()!=null&&user1.getPassword()!=null){
            //返回:不可用
            out.print("身份证已被注册");
        }else{
            //可以用
            out.print("");
        }
    }
    protected void checkcardId2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cardId = request.getParameter("cardId");
        //2.数据库查询
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        UserService service = new UserServiceImpl();
        User user = new User();
        user.setCardId(cardId);
        user.setPassword(null);
        User user1=service.login(user);
        if(user1==null||user1.getCardId()==null||user1.getPassword()==null){
            //返回:不可用
            out.print("身份证未注册，请检查或注册用户");
        }else{
            //可以用
            out.print("");
        }
    }


    }
