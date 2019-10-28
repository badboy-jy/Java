package com.jmd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jmd.db.DBUser;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		doPost(req,resp);
	};
	
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request--->" + request.getRequestURL() + "===="
				+ request.getParameterMap().toString());
		String username = request.getParameter("username"); 
		String password = request.getParameter("password");
		response.setContentType("text/html;charset=utf-8");
		if (username == null || username.equals("") || password == null
				|| password.equals("")) {
			System.out.println("用户名或密码为空");
			return;
		} 
		DBUser dbuser = new DBUser();
		dbuser.openConnect();
		String istrue;
		if (dbuser.isExistInDB(username, password)) {
			istrue = "succeed";
		} else {
			istrue = "failure";
	
		try{
			response.getWriter().print(istrue);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			response.getWriter().close(); 
		}
		dbuser.closeConnect(); 
	}
	}
	}
