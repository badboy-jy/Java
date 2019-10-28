package com.jmd.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jmd.domain.UserBean;

public class DBMyself {
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
			rs = sta.executeQuery("select * from myself");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}


	// 注册 将用户名和密码插入到数据库
	public boolean insertDataToDB(String land_geren,String land_out,String land_in,String land_now,String pinzhong1,String pinzhong2,String pinzhong3,String pinzhong4,String pin1,String pin2,String pin3,String pin4,String gongshi) {
		String sql = " insert into myself ( myself_land_geren , myself_land_out,myself_land_in,myself_land_now,myself_pinzhong1,myself_pinzhong2,myself_pinzhong3,myself_pinzhong4,myself_pin1,myself_pin2,myself_pin3,myself_pin4,myself_gongshi ) values ( "
	+ "'" + land_geren + "', " + "'" + land_out + "', "+ "'" + land_in + "' ,"+ "'" + land_now + "', "+ "'" + pinzhong1 + "', "+ "'" + pinzhong2 + "', "+ "'" + pinzhong3 + "', "+ "'" + pinzhong4 + "', "+ "'" + pin1 + "', "+ "'" + pin2 + "', "+ "'" + pin3 + "', "+ "'" + pin4 + "', "+ "'" + gongshi + "' )";
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
				user.setId(rs.getInt("myself_id"));
				user.setLandgeren(rs.getString("myself_land_geren"));
				user.setLandOut(rs.getString("myself_land_out"));
				user.setLandIn(rs.getString("myself_land_in"));
				user.setLandNow(rs.getString("myself_land_now"));
				user.setPinzhong1(rs.getString("myself_pinzhong1"));
				user.setPinzhong2(rs.getString("myself_pinzhong2"));
				user.setPinzhong3(rs.getString("myself_pinzhong3"));
				user.setPinzhong4(rs.getString("myself_pinzhong4"));
				user.setPin1(rs.getString("myself_pin1"));
				user.setPin2(rs.getString("myself_pin2"));
				user.setPin3(rs.getString("myself_pin3"));
				user.setPin4(rs.getString("myself_pin4"));
				user.setGongshi(rs.getString("myself_gongshi"));
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
