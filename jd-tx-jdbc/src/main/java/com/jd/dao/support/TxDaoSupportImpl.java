package com.jd.dao.support;

import com.jd.dao.TxDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wangyingjie1
 * @date 2017/10/16 22:24
 */
public class TxDaoSupportImpl extends JdbcDaoSupport implements TxDao {

    @Override
    public void saveTx(String sql) {
        this.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
            @Override
            public Object doInConnection(Connection con) throws SQLException, DataAccessException {

                // todo 数据库操作

                return null;
            }
        });
    }
}
