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
import com.jmd.db.DBNews;
import com.jmd.db.DBPingjia;
import com.jmd.db.DBUser;
import com.jmd.domain.BaseBean;
import com.jmd.domain.UserBean;

public class PingjiaOutServlet extends HttpServlet {
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
		String pingjia = request.getParameter("pingjia");
		response.setContentType("text/html;charset=utf-8");
		if (name == null || name.equals("") || pingjia == null || pingjia.equals("")) {
			System.out.println("用户名或消息为空");
			return;
		} // 请求数据库
		DBPingjia dbpingjia = new DBPingjia();
		dbpingjia.openConnect();
		// 打开数据库连接
		BaseBean data = new BaseBean(); // 基类对象，回传给客户端的json对象
		UserBean userBean = new UserBean(); //user的对象
	 if (!dbpingjia.insertDataToDB(name, pingjia)) {
			//注册成功
			data.setCode(0);
			data.setMsg("发布成功!!");
			ResultSet rs = dbpingjia.getUser();
			int id = -1;
			if (rs != null) {
				try {
					while (rs.next()) {
						if (rs.getString("pingjia_name").equals(name)
								&& rs.getString("pingjia_p").equals(pingjia)) {
							id = rs.getInt("pingjia_id");
						}
					}
					userBean.setId(id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			userBean.setName(name);
			userBean.setPj(pingjia);
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
		dbpingjia.closeConnect(); //关闭数据库连接
	}
}
