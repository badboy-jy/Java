package com.zhangjingyi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import com.alibaba.druid.pool.DruidDataSource;

public class DbUtils {

    //1.jdbc api
    protected static Connection con=null;
    protected  static PreparedStatement pps=null;
    protected  static ResultSet rs=null;
    private  static String driverClass;
    private  static String url;
    private   static String username;
    private   static String password;
    private static String init;

    //创建德鲁伊的对象
    static DruidDataSource datasource=new DruidDataSource();
    //2.加载驱动
    static {
        //1.从属性文件中获取信息
        ResourceBundle b=ResourceBundle.getBundle("jdbc");
        driverClass=b.getString("driverClassName");
        url=b.getString("url");
        username=b.getString("user");
        password=b.getString("password");
        //2.赋值给德鲁伊对象  默认MaxActive是8个
        datasource.setDriverClassName(driverClass);
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        //datasource.setMaxActive(5);//设置最大的连接活跃数量
        System.out.println("max="+datasource.getMaxActive());
    }

    //3.获得连接
    public static  Connection  getConn() {
        try {
            con= datasource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }

    //4.打开通道
    protected  PreparedStatement getpps(String sql) {
        try {
            pps= con.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pps;
    }
    //5.绑定参数  params保存给占位符所赋的值
    private  void param(List params) {
        try {

            if(params!=null&&params.size()>0) {
                for (int i = 0; i < params.size(); i++) {
                    pps.setObject(i+1,params.get(i));
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //6.增删改
    protected  int  update(String sql,List params) {
        int k=0;
        try {
            getConn();
            getpps(sql);
            param(params);
            k=pps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return k;
    }
    //7.查询
    protected  ResultSet  query(String sql,List params) {

        try {
            getConn();
            getpps(sql);
            param(params);
            rs=pps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }

    //8.关闭资源
    protected void closeall() {
        try {
            if(rs!=null) rs.close();
            if(pps!=null)pps.close();
            if(con!=null)con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}

