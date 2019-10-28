package com.jmd.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jmd.db.DBLandHelper;
import com.jmd.db.DBLandRent;
import com.jmd.db.DBSalesOrder;
import com.jmd.db.DBUser;
import com.jmd.db.DBVegetableOrder;
import com.jmd.domain.BaseBean;
import com.jmd.domain.UserBean;

public class SalesOrderFServlet extends HttpServlet {
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
		String shuliang = request.getParameter("shuliang");
		String phone = request.getParameter("phone");
		String weixin = request.getParameter("weixin");
		String pinzhong = request.getParameter("pinzhong");
		response.setContentType("text/html;charset=utf-8");
		if (name == null || name.equals("") || phone == null || phone.equals("")) {
			System.out.println("用户名或手机号为空");
			return;
		} // 请求数据库
DBSalesOrder dbsalesorder = new DBSalesOrder();
dbsalesorder.openConnect();
		// 打开数据库连接
		BaseBean data = new BaseBean(); // 基类对象，回传给客户端的json对象
		UserBean userBean = new UserBean(); //user的对象
		if (dbsalesorder.isExistInDB(name, pinzhong)) {
			// 判断账号是否存在
			data.setCode(-1);
			data.setData(userBean);
			data.setMsg("该信息已存在");
		} else if (!dbsalesorder.insertDataToDB(name,pinzhong,phone,weixin,shuliang)) {
			//注册成功
			data.setCode(0);
			data.setMsg("发布成功!!");
			ResultSet rs = dbsalesorder.getUser();
			int id = -1;
			if (rs != null) {
				try {
					while (rs.next()) {
						if (rs.getString("salesorder_name").equals(name)
								&& rs.getString("salesorder_phone").equals(phone)) {
							id = rs.getInt("salesorder_id");
						}
					}
					userBean.setId(id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			userBean.setName(name);
			userBean.setPhone(phone);
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
		dbsalesorder.closeConnect(); //关闭数据库连接
	}
}
