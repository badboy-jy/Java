package com.jmd.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jmd.domain.UserBean;

public class DBLandHelper {
	private Connection conn;
	private String url = "jdbc:mysql://127.0.0.1:3306/gvegetabledatabase?serverTimezone=UTC"; //连接数据库gvegetabledatabase
	private String user = "root"; //数据库gvegetabledatabase的用户名 ָ
	private String password = "123456"; // 数据库gvegetabledatabase的密码
	private Statement sta;
	private ResultSet rs; // 打开数据库连接

	public void openConnect() {
		try {
			//  加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);// 创建数据库连接
			if (conn != null) {
				System.out.println("数据库gvegetabledatabase连接成功"); // 连接成功的提示信息
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// 获得查询   表后的数据集
	public ResultSet getUser() {
		// 创建 statement对象
		try {
			sta = conn.createStatement(); //执行SQL查询语句
			rs = sta.executeQuery("select * from landhelper");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	//判断数据库中是否存在某个用户名及其密码,注册和登录的时候判断
	public boolean isExistInDB(String name, String phone) {
		boolean isFlag = false; // 创建 statement对象
		try {
			System.out.println("判断用户名手机号");
			sta = conn.createStatement(); // 执行SQL查询语句
			rs = sta.executeQuery("select * from landhelper");// 获得结果集
			if (rs != null) {
				while (rs.next()) { // 遍历结果集
					if (rs.getString("landhelper_name").equals(name)) {
						if (rs.getString("landhelper_phone").equals(phone)) {
							isFlag = true;
							break;
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			isFlag = false;
		}
		return isFlag;
	}


	// 注册 将用户名和密码插入到数据库
	public boolean insertDataToDB(String name,String price,String gongneng,String phone,String weixin) {
		String sql = " insert into landhelper ( landhelper_name , landhelper_price,landhelper_gongneng,landhelper_phone,landhelper_weixin ) values ( "
	+ "'" + name + "', " + "'" + price + "', "+ "'" + gongneng + "' ,"+ "'" + phone + "', "+ "'" + weixin + "' )";
		try {
			sta = conn.createStatement();
			System.out.println("向数据库中插入数据");
			// 执行SQL查询语句
			return sta.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	public List<UserBean> fandAll(){
		
		rs = getUser();
		List<UserBean> list=new ArrayList();
		if(rs!=null){
			try {
				while(rs.next()){
				UserBean user = new UserBean();
				user.setId(rs.getInt("landhelper_id"));
				user.setName(rs.getString("landhelper_name"));
				user.setGongneng(rs.getString("landhelper_gongneng"));
				user.setPrice(rs.getString("landhelper_price"));
				user.setPhone(rs.getString("landhelper_phone"));
				list.add(user);
	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
		

	// 关闭数据库连接
	public void closeConnect() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (sta != null) {
				sta.close();
			}
			if (conn != null) {
				conn.close();
			}
			System.out.println("关闭数据库连接成功");
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
