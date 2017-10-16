package com.jd.template.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: wangyingjie1
 * @version: 1.0
 * @createdate: 2017-10-16 18:00
 * @see org.springframework.jdbc.core.JdbcTemplate
 */
public class JdbcTemplate {

    private DataSource dataSource;

    /**
     * Construct a new JdbcTemplate for bean usage.
     * <p>Note: The DataSource has to be set before using the instance.
     *
     * @see #setDataSource
     */
    public JdbcTemplate() {
    }

    /**
     * Construct a new JdbcTemplate, given a DataSource to obtain connections from.
     * <p>Note: This will not trigger initialization of the exception translator.
     *
     * @param dataSource the JDBC DataSource to obtain connections from
     */
    public JdbcTemplate(DataSource dataSource) {
        setDataSource(dataSource);

        // spring 扩展接口
        // afterPropertiesSet();
    }

    /**
     * Set the JDBC DataSource to obtain connections from.
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Return the DataSource used by this template.
     */
    public DataSource getDataSource() {
        return this.dataSource;
    }

    public <T> T execute(ConnectionCallback<T> action) throws DataAccessException {
        Assert.notNull(action, "Callback object must not be null");

        // DataSourceUtils 数据源操作工具类，最佳实践
        Connection con = DataSourceUtils.getConnection(getDataSource());
        try {
            Connection conToUse = con;
//            if (this.nativeJdbcExtractor != null) {
//                // Extract native JDBC Connection, castable to OracleConnection or the like.
//                conToUse = this.nativeJdbcExtractor.getNativeConnection(con);
//            }
//            else {
//                // Create close-suppressing Connection proxy, also preparing returned Statements.
//                conToUse = createConnectionProxy(con);
//            }

            return action.doInConnection(conToUse);
        } catch (SQLException ex) {
            // Release Connection early, to avoid potential connection pool deadlock
            // in the case when the exception translator hasn't been initialized yet.
            DataSourceUtils.releaseConnection(con, getDataSource());
            con = null;
            // throw getExceptionTranslator().translate("ConnectionCallback", getSql(action), ex);
            return null;
        } finally {
            DataSourceUtils.releaseConnection(con, getDataSource());
        }
    }
}