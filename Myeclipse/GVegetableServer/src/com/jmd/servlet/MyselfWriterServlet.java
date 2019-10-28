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
import com.jmd.db.DBMyself;
import com.jmd.db.DBUser;
import com.jmd.db.DBWaterOrder;
import com.jmd.domain.BaseBean;
import com.jmd.domain.UserBean;

public class MyselfWriterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		doPost(request, response);
	};
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request--->" + request.getRequestURL() + "===="
				+ request.getParameterMap().toString());
		String land_geren = request.getParameter("land_geren"); // 获取客户端传过来的参数
		String land_out = request.getParameter("land_out");
		String land_in = request.getParameter("land_in");
		String land_now = request.getParameter("land_now");
		String pinzhong1 = request.getParameter("pinzhong1");
		String pinzhong2 = request.getParameter("pinzhong2");
		String pinzhong4 = request.getParameter("pinzhong4");
		String pin1= request.getParameter("pin1");
		String pin2= request.getParameter("pin2");
		String pin3= request.getParameter("pin3");
		String pinzhong3 = request.getParameter("pinzhong3");
		String pin4 = request.getParameter("pin4");
		String gongshi = request.getParameter("gongshi");

		response.setContentType("text/html;charset=utf-8");
		
		DBMyself dbmyself = new DBMyself();
		dbmyself.openConnect();
		// 打开数据库连接
		BaseBean data = new BaseBean(); // 基类对象，回传给客户端的json对象
		UserBean userBean = new UserBean(); //user的对象
	  if (!dbmyself.insertDataToDB(land_geren,land_out,land_in,land_now,pinzhong1,pinzhong2,pinzhong3,pinzhong4,pin1,pin2,pin3,pin4,gongshi)) {
			//注册成功
			data.setCode(0);
			data.setMsg("发布成功!!");
			ResultSet rs = dbmyself.getUser();
			int id = -1;
			if (rs != null) {
				try {
					while (rs.next()) {
						if (rs.getString("myself_land_geren").equals(land_geren)
								&& rs.getString("myself_pin1").equals(pin1)) {
							id = rs.getInt("myself_id");
						}
					}
					userBean.setId(id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			userBean.setLandgeren(land_geren);
			userBean.setPin1(pin1);
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
		dbmyself.closeConnect(); //关闭数据库连接
	}
}
