package com.jmd.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jmd.domain.UserBean;

public class DBVegetableOrder {
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

	// 获得查询user表后的数据集
	public ResultSet getUser() {
		// 创建 statement对象
		try {
			sta = conn.createStatement(); //执行SQL查询语句
			rs = sta.executeQuery("select * from vegetableorder ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	//判断数据库中是否存在某个用户名及其密码,注册和登录的时候判断
	public boolean isExistInDB(String name, String pinzhong) {
		boolean isFlag = false; // 创建 statement对象
		try {
			System.out.println("判断用户名手机号");
			sta = conn.createStatement(); // 执行SQL查询语句
			rs = sta.executeQuery("select * from vegetableorder");// 获得结果集
			if (rs != null) {
				while (rs.next()) { // 遍历结果集
					if (rs.getString("vegetableorder_name").equals(name)) {
						if (rs.getString("vegetableorder_pinzhong").equals(pinzhong)) {
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
	public boolean insertDataToDB(String name,String price,String pinzhong,String phone,String weixin,String shuliang) {
		String sql = " insert into vegetableorder ( vegetableorder_name , vegetableorder_price,vegetableorder_pinzhong,vegetableorder_phone,"
				+ "vegetableorder_weixin,vegetableorder_shuliang) values ( "
	+ "'" + name + "', " + "'" + price + "', "+ "'" + pinzhong + "' ,"+ "'" + phone + "', "+ "'" + weixin + "', "+ "'" + shuliang + "'  )";
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
	
	
	
	
	public List<UserBean> fandAll(String pinzhong){
		
		rs = getUser();
		List<UserBean> list=new ArrayList();
		if(rs!=null){
			try {
				while(rs.next()){
					if(rs.getString("vegetableorder_pinzhong").equals(pinzhong)){
						UserBean user = new UserBean();
						user.setId(rs.getInt("vegetableorder_id"));
						user.setName(rs.getString("vegetableorder_name"));
						user.setPrice(rs.getString("vegetableorder_price"));
						user.setPhone(rs.getString("vegetableorder_phone"));
						user.setShuliang(rs.getString("vegetableorder_shuliang"));
						list.add(user);
	}}
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
