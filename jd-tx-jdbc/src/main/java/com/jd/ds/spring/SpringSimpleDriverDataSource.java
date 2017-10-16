package com.jd.ds.spring;

import com.jd.common.DBConstants;
import com.mysql.jdbc.Driver;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * spring 封装的数据链接 ds，该ds并不支持池化
 *
 * @author: wangyingjie1
 * @version: 1.0
 * @createdate: 2017-10-16 18:38
 */
public class SpringSimpleDriverDataSource {

    public Connection getConnection() throws SQLException {
        // 指定 Driver
        SimpleDriverDataSource ds = new SimpleDriverDataSource(new Driver(), DBConstants.URL);

        /// ds.setDriverClassName(DBConstants.DRIVER);
        /// ds.setUrl(DBConstants.URL);

        ds.setUsername(DBConstants.USERNAME);
        ds.setPassword(DBConstants.PASSWORD);

        Connection actualCon = ds.getConnection();

        return actualCon;
    }

}