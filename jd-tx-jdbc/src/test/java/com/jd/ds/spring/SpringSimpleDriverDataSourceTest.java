package com.jd.ds.spring;

import org.junit.Test;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class SpringSimpleDriverDataSourceTest {

    private SpringSimpleDriverDataSource ds = new SpringSimpleDriverDataSource();

    @Test
    public void testConnection() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1、获取数据库连接
            conn =  ds.getConnection();;
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
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(stmt);
            JdbcUtils.closeConnection(conn);
        }
    }

}