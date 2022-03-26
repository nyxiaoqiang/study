package com.study.jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) throws SQLException {
        //1、注册驱动
        DriverManager.registerDriver(new Driver());
        //2、获取连接
        String url = "jdbc:mysql://192.168.253.111:3306/study";
        String user = "root";
        String pwd = "root";
        Connection connection = DriverManager.getConnection(url, user, pwd);
        //3、执行sql
        String sql = "select * from blog";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        //4、查看结果
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            Date createTime = resultSet.getDate("create_time");
            System.out.println("id:"+id+"--title:"+title+"--author:"+author+"--createTime:"+createTime);
        }
        //5、关闭连接
        resultSet.close();
        connection.close();
    }
}
