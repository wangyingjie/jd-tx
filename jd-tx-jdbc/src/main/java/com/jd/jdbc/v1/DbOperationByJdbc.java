package com.jd.jdbc.v1;

import com.jd.common.DBConstants;

import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wangyingjie1
 * @Date 2017/10/15 22:16
 */
public class DbOperationByJdbc {

    /**
     * 使用JDBC连接并操作mysql数据库
     */
    public static void selectData() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(DBConstants.DRIVER);
            // 2、获取数据库连接
            conn = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
            // 3、获取数据库操作对象
            stmt = conn.createStatement();
            // 4、定义操作的SQL语句
            String sql = "select * from tx where id > 0";
            // 5、执行数据库操作
            rs = stmt.executeQuery(sql);
            // 6、获取并操作结果集
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "======" + rs.getString("num"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7、关闭对象，回收数据库资源
            if (rs != null) {
                try {
                    //关闭结果集对象
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    // 关闭数据库操作对象
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    // 关闭数据库连接对象
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用JDBC连接并操作mysql数据库
     */
    public static void insertData() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(DBConstants.DRIVER);

            // 2、获取数据库连接
            conn = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);

            System.out.println("conn.getAutoCommit=============>" + conn.getAutoCommit());
            // conn.setAutoCommit(false);

            // 3、获取数据库操作对象
            stmt = conn.createStatement();

            // 4、定义操作的SQL语句
            String sql = "INSERT INTO tx  VALUES (" + ThreadLocalRandom.current().nextInt(10, 10000000) + ", 7)";

            // 5、执行数据库操作
            int count = stmt.executeUpdate(sql);

            System.out.println("count==========" + count);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7、关闭对象，回收数据库资源
            if (rs != null) {
                try {
                    //关闭结果集对象
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    // 关闭数据库操作对象
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    // 关闭数据库连接对象
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
