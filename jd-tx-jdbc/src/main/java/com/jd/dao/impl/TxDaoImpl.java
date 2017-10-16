package com.jd.dao.impl;

import com.jd.dao.TxDao;
import com.jd.ds.spring.SpringManagerDataSource;
import com.jd.template.jdbc.JdbcTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wangyingjie1
 * @date 2017/10/16 22:24
 */
public class TxDaoImpl implements TxDao {

    /**
     * 所有 Dao 均需要注入 JdbcTemplate ，然后 JdbcTemplate 又需要注入 DataSource
     * 源于此 xxxDaoSupport 应运而生
     */
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringManagerDataSource.getDataSource());

    @Override
    public void saveTx(String sql) {
        jdbcTemplate.execute(new ConnectionCallback<Object>() {

            @Override
            public Object doInConnection(Connection con) throws SQLException, DataAccessException {

                // todo 数据库操作

                return null;
            }
        });
    }
}
