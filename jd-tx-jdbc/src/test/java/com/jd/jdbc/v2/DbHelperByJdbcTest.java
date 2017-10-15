package com.jd.jdbc.v2;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbHelperByJdbcTest {

    private DbHelperByJdbc dbHelperByJdbc = new DbHelperByJdbc();

    @Test
    public void testSelect() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 2、获取数据库连接
            conn = DbHelperByJdbc.getConnection();
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
            DbHelperByJdbc.close(conn, stmt, rs);
        }
    }

    @Test
    public void testSelect2() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 2、获取数据库连接
            conn = DbHelperByJdbc.getConnection2();
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
            DbHelperByJdbc.close(conn, stmt, rs);
        }
    }

}