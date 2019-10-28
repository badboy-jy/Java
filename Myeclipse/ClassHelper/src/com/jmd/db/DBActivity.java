package com.jmd.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jmd.domain.UserBean;

public class DBActivity {
	private Connection conn;
	private String url = "jdbc:mysql://127.0.0.1:3306/classhelper?serverTimezone=UTC"; //连接数据库gvegetabledatabase
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
				System.out.println("数据库classhelper连接成功"); // 连接成功的提示信息
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
			rs = sta.executeQuery("select * from activity");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	//判断数据库中是否存在某个用户名及其密码,注册和登录的时候判断
	


	// 注册 将用户名和密码插入到数据库
	public boolean insertDataToDB(String activityname,String activityplace,String activityperson,String activityphone,String activitycontent) {
		String sql = " insert into activity ( activity_name,activity_place,activity_person,activity_phone,activity_content ) values ( "
	+ "'" + activityname + "', "+ "'" + activityplace + "', "+ "'" + activityperson + "', "+ "'" + activityphone + "', "+ "'" + activitycontent + "' )";
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
				user.setId(rs.getInt("activity_id"));
				user.setName(rs.getString("activity_name"));
				user.setPlace(rs.getString("activity_place"));
				user.setPerson(rs.getString("activity_person"));
				user.setPhone(rs.getString("activity_phone"));
				user.setContent(rs.getString("activity_content"));
			
			
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
