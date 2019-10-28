package com.jmd.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jmd.db.DBHuodong;
import com.jmd.db.DBJilu;
import com.jmd.db.DBSign;
import com.jmd.db.DBStudent;
import com.jmd.db.DBUser;
import com.jmd.domain.BaseBean;
import com.jmd.domain.UserBean;

public class JoinServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		doPost(request, response);
	};
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request--->" + request.getRequestURL() + "===="
				+ request.getParameterMap().toString());
		String name = request.getParameter("name"); // 获取客户端传过来的参数

		response.setContentType("text/html;charset=utf-8");
		if (name == null || name.equals("") ) {
			System.out.println("名字为空");
			return;
		} // 请求数据库
		DBHuodong dbhuodong = new DBHuodong();
		dbhuodong.openConnect();
		// 打开数据库连接
		BaseBean data = new BaseBean(); // 基类对象，回传给客户端的json对象
		UserBean userBean = new UserBean(); //user的对象
		if (!dbhuodong.insertDataToDB(name)) {
			//注册成功
			data.setCode(0);
			data.setMsg("报名成功!!");
			ResultSet rs = dbhuodong.getUser();
		
			
			userBean.setName(name);
		
			data.setData(userBean);
		} else {
			//注册不成功
			data.setCode(500);
			data.setData(userBean);
			data.setMsg("数据库错误");
		}
		String msg = data.getMsg();
		Gson gson = new Gson();
		String json = gson.toJson(msg);
		// 将对象转化成json字符串
		try {
			response.getWriter().println(json);
			//将json数据传给客户端
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.getWriter().close(); // 关闭流
		}
		dbhuodong.closeConnect(); //关闭数据库连接
	}
}
