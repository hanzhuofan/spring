package com.bilibili.mybatis.jdbc;

import com.bilibili.mybatis.entity.User;
import com.google.common.collect.Lists;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author hanzhuofan
 * @date 2020/5/23 10:
 */
public class JDBC {
  public static void main(String[] args) {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    ArrayList<User> userList = Lists.newArrayList();
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection("");
      statement = connection.createStatement();
      resultSet = statement.executeQuery("select * from user");
      while (resultSet.next()) {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        int age = resultSet.getInt(3);
        userList.add(new User(id, name, age));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
