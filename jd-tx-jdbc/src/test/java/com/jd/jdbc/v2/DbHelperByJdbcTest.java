package com.jd.jdbc.v2;

import org.junit.Test;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;

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

    @Test
    public void testSelect3() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1、获取数据库连接
            DbHelperByJdbc.getConnection3();

            conn = (Connection) TransactionSynchronizationManager.getResource(DbHelperByJdbc.CONNECTION);

            // 2、获取数据库操作对象
            stmt = conn.createStatement();
            // 3、定义操作的SQL语句
            String sql = "select * from tx where id > 0";
            // 4、执行数据库操作
            rs = stmt.executeQuery(sql);
            // 5、获取并操作结果集
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "======" + rs.getString("num"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、释放资源
            DbHelperByJdbc.close(conn, stmt, rs);
        }
    }

    @Test
    public void testInsert1() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 2、获取数据库连接
            DbHelperByJdbc.getConnection3();

            conn = (Connection) TransactionSynchronizationManager.getResource(DbHelperByJdbc.CONNECTION);

            // 3、获取数据库操作对象
            stmt = conn.createStatement();
            // 4、定义操作的SQL语句
            String sql = "INSERT INTO tx(id, num) VALUES (23, 23)";

            // 5、执行数据库操作
            stmt.executeUpdate(sql);

            rs = stmt.executeQuery("select * from tx");
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
    public void testInsert2() {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 2、获取数据库连接
            DbHelperByJdbc.getConnection3();

            conn = (Connection) TransactionSynchronizationManager.getResource(DbHelperByJdbc.CONNECTION);

            String sql = "INSERT INTO tx(id, num) VALUES (?, ?)";

            // 3、获取数据库操作对象
            ps = conn.prepareStatement(sql);

            // 4、定义操作的SQL语句

            Integer id = ThreadLocalRandom.current().nextInt(10, 10000000);

            ps.setInt(1, id);
            ps.setInt(2, id);

            // 5、执行数据库操作
            int count = ps.executeUpdate();

            //ps.execute(sql);

            System.out.println("execute======" + count);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbHelperByJdbc.close(conn, ps, null);
        }
    }

}