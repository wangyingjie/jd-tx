package com.jd.jdbc.v2;

import com.jd.common.DBConstants;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.*;

/**
 * @author wangyingjie1
 * @Date 2017/10/15 22:16
 */
public class DbHelperByJdbc {

    public static final String CONNECTION = "conn";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(DBConstants.DRIVER);
            // 2、获取数据库连接
            connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
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
            //Class.forName(DRIVER);

            // 2、获取数据库连接
            connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }

    public static void getConnection3() {
        Connection connection = null;
        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(DBConstants.DRIVER);

            // 2、获取数据库连接
            connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);

            TransactionSynchronizationManager.bindResource(CONNECTION, connection);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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
