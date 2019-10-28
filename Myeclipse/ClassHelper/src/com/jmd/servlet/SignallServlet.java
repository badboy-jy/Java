package com.jmd.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jmd.db.DBSign;
import com.jmd.db.DBStudent;
import com.jmd.domain.UserBean;

public class SignallServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		doPost(request, response);
	};
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request--->" + request.getRequestURL() + "===="
				+ request.getParameterMap().toString());
		response.setContentType("text/html;charset=utf-8");
		
		DBStudent dbstudent = new DBStudent(); 
		dbstudent.openConnect();
		List<UserBean> ubList0=dbstudent.fandAll();
		DBSign dbUtils = new DBSign();
		dbUtils.openConnect();
		List<UserBean> ubList=dbUtils.fandAll();
		List<UserBean> ubList1=dbUtils.fandAll1();
		List<UserBean> ubList2=dbUtils.fandAll2();
		int i = ubList0.size()-ubList.size();
		String all = "已到人数："+ ubList1.size() + "请假人数：" + ubList2.size() + "未到人数：" +i;
		
		try {
			Gson gson = new Gson();
			String json = gson.toJson(all);
			response.getWriter().println(json);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		 finally {
			response.getWriter().close(); // 
	}
		dbUtils.closeConnect(); //
	}
	}

	
