package com.badboyjy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.badboyjy.bean.User;
import com.badboyjy.service.UserService;
import com.badboyjy.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	UserService userService  = new UserServiceImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取用户信息  姓名,密码，邮箱
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String email = request.getParameter("email");
				//项目路径
				String contextPath = request.getContextPath();
				// 此时，注册前，得校验，用户名是否已经存在
				User user = userService.checkUserByName(username);
				if(user==null){//当用户名不存在，才可以注册
					//2. 封装成user对象
					//3. 调userService里的 注册方法 regist()
					int len = userService.regist(new User(null,username,password,email));
					if(len>0){
						//跳转到注册成功页面
						request.getRequestDispatcher("pages/user/regist_success.html").forward(request, response);
					}
					else{
						//重定向到注册页面
						response.sendRedirect(contextPath+"/pages/user/regist.html");
					}
				}
				else{
					//重定向到注册页面
					response.sendRedirect(contextPath+"/pages/user/regist.html");
					
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
