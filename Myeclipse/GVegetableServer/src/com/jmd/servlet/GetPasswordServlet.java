package com.jmd.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jmd.db.DBUser;
import com.jmd.domain.UserBean;

public class GetPasswordServlet extends HttpServlet {

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
		String phone = request.getParameter("phone");
		response.setContentType("text/html;charset=utf-8");
		if (username == null || username.equals("") || phone == null
				|| phone.equals("")) {
			System.out.println("用户名或手机号为空");
			return;
		} 
		DBUser dbuser = new DBUser();
		dbuser.openConnect();
		String pwd = dbuser.fandAll(username, phone);
		String istrue;
		
		if (dbuser.findInDB(username, phone)) {
			
			istrue = pwd ;
		} else {
			istrue = "该用户未注册";
		}
		Gson gson = new Gson();
		String json = gson.toJson(istrue);
		try{
			response.getWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			response.getWriter().close(); 
		}
		dbuser.closeConnect(); 
	}
	}
	
