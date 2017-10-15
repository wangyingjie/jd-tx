package com.jd.jdbc.v2;

import java.sql.*;

/**
 * @author wangyingjie1
 * @Date 2017/10/15 22:16
 */
public class DbHelperByJdbc {

    /**
     * 数据库驱动类名的字符串
     */
    private static String driver = "com.mysql.jdbc.Driver";

    /**
     * 数据库连接串
     */
    private static String url = "jdbc:mysql://127.0.0.1:3306/test";

    /**
     * 用户名
     */
    private static String username = "root";

    /**
     * 密码
     */
    private static String password = "111111";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(driver);
            // 2、获取数据库连接
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }

    public static Connection getConnection2() {
        Connection connection = null;
        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            //Class.forName(driver);

            // 2、获取数据库连接
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        // 7、关闭对象，回收数据库资源
        if (rs != null) { //关闭结果集对象
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) { // 关闭数据库操作对象
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) { // 关闭数据库连接对象
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
