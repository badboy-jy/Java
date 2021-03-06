package com.badboyjy.servlet;

import com.badboyjy.bean.User;
import com.badboyjy.service.UserService;
import com.badboyjy.service.impl.UserServiceImpl;
import com.badboyjy.utils.DataUtils;
import com.badboyjy.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends HttpServlet {
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
    protected void selectalluser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得参数
        String userName = request.getParameter("userName");
        if (userName==null||"".equals(userName) ){
            userName="";
        }
        //存放参数值 给前端使用
        request.setAttribute("username", userName);
        int userid = (int) request.getSession().getAttribute("userid");
        request.setAttribute("usid",userid);
        request.setAttribute("size", DataUtils.PAGESIZE);

        String index = request.getParameter("index");//接受页码值
        int pageindex = 1;
        if (index != null) {
            pageindex = Integer.parseInt(index);
        }
        //调取service方法
        UserService service = new UserServiceImpl();
        List<User> list = service.findalluser(userName, pageindex, DataUtils.PAGESIZE);

        //查询总条数，通过总条数算出总页数
        int totalcount = service.totalcount(userName);
        PageUtils<User> page = new PageUtils<>();
        page.setPageIndex(pageindex);
        page.setTotalCount(totalcount);
        page.setDataList(list);
        request.setAttribute("p1", page);
        //3.跳转页面
        request.getRequestDispatcher("/userAdmin.jsp").forward(request, response);
    }
    protected void adduser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String auth = request.getParameter("auth");
        //调取对象实现方法
        UserService userService = new UserServiceImpl();
        User user = new User();
        String sexx = "";
        if ("0".equals(sex)){
            sexx="女";
        }if ("1".equals(sex)){
            sexx="男";
        }
        user.setUserName(username);
        user.setUserPass(password);
        user.setUserSex(sexx);
        user.setUserPhone(mobile);
        user.setUserAddress(address);
        int agee=0;
        int authh=0;
        if (!"".equals(age)){
            agee=Integer.parseInt(age);
        }
        if (!"".equals(auth)){
            authh=Integer.parseInt(auth);
        }
        user.setUserAge(agee);
        user.setUserQuanXian(authh);
        int i = userService.adduser(user);
        //跳转页面
        if (i > 0) {
            response.sendRedirect("/user?method=selectalluser");
        } else {
            response.sendRedirect("/userAdd.jsp");
        } //调取service方法
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String uid = request.getParameter("uid");
        //2.调取service方法
        UserService service = new UserServiceImpl();
        int i = service.deletebyid(Integer.parseInt(uid));
        //3.跳转页面
        //加弹窗 显示操作成功与否
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (i > 0) {
            out.print("<script>alert('删除成功');location.href='/user?method=selectalluser'</script>");
        } else {
            out.print("<script>alert('删除失败');location.href='/user?method=selectalluser'</script>");
        }
    }
    protected void findbyid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        //2.调取service方法
        //查找存放 findbyid  结果eBookEntry到entry  给前端
        UserService service = new UserServiceImpl();
        User user = service.findbyid(Integer.parseInt(uid));
        request.setAttribute("user", user);
        request.setAttribute("uid",uid);
        //3.跳转页面
        request.getRequestDispatcher("/userUpdate.jsp").forward(request, response);
    }
    public void updateuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String uid = request.getParameter("uid");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        //2.调取service方法
        UserService userService = new UserServiceImpl();
        User user = new User();
        String sexx = "";
        if ("0".equals(sex)){
            sexx="女";
        }if ("1".equals(sex)){
            sexx="男";
        }
        user.setUserId(Integer.parseInt(uid));
        user.setUserName(username);
        user.setUserPass(password);
        user.setUserSex(sexx);
        user.setUserPhone(mobile);
        user.setUserAddress(address);
        int agee=0;
        if (!"".equals(age)){
            agee=Integer.parseInt(age);
        }
        user.setUserAge(agee);

        int i = userService.update(user);
        //3.跳转页面
        if(i>0){
            response.sendRedirect("/user?method=selectalluser");
        }else{
            response.sendRedirect("/user?method=findbyid&uid="+uid);
        }
    }


}
