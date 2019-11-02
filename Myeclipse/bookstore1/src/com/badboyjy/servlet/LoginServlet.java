package com.badboyjy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.badboyjy.bean.User;
//import com.badboyjy.dao.UserDao;
//import com.badboyjy.dao.impl.UserDaoImpl;
import com.badboyjy.service.UserService;
import com.badboyjy.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String contextPath = request.getContextPath();
	 response.setContentType("text/html;charset=UTF-8");
	 request.setCharacterEncoding("UTF-8");
	 String name = request.getParameter("username");
	 String password = request.getParameter("password");
	 User user = new User(null,name,password,null);
	 UserService userService = new UserServiceImpl();
	 User user2 = userService.login(user);
//	 UserDao userDao = (UserDao) new UserDaoImpl();
//	 User user2 = userDao.getUser(user);
	 if (user2!=null) {
		request.getRequestDispatcher("pages/user/login_success.html").forward(request, response);
		
	}else{
		response.sendRedirect(contextPath+"/pages/user/login.html");
		
	}
	 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
