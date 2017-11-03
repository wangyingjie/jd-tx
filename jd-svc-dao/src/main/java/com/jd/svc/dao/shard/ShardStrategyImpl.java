package com.jd.svc.dao.shard;

import com.google.code.shardbatis.strategy.ShardStrategy;
import com.jd.svc.domain.SvcBookingOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author: wangyingjie1
 * @version: 1.0
 * @createdate: 2017-11-01 16:14
 */
public class ShardStrategyImpl implements ShardStrategy {

    private static final Logger logger = LoggerFactory.getLogger(ShardStrategyImpl.class);

    public static Integer DB_NUM = 2;//分库的数量
    public static Integer TABLE_NUM_PER_DB = 2;//每个库分表的数量


    /**
     * 得到实际表名
     *
     * @param originName 逻辑表名,一般是没有前缀或者是后缀的表名
     * @param params     mybatis执行某个statement时使用的参数
     * @param mapperId   mybatis配置的statement id
     * @return
     */
    @Override
    public String getTargetTableName(String originName, Object params, String mapperId) {
        Long temp = Math.abs(getRouterValue(params));

        Long tableIndex = (temp / DB_NUM) % TABLE_NUM_PER_DB;
        String targetTable = String.format(originName + "_%03d", tableIndex + 1);

        logger.debug("erp:{},进入{}表,库数：{},表数：{}", temp, targetTable, DB_NUM, TABLE_NUM_PER_DB);

        return targetTable;
    }

    /**
     * 跟据参数获取路由值
     *
     * @param obj mybatis执行sql参数
     * @return
     */
    public Long getRouterValue(Object obj) {
        if (obj == null) {
            throw new RuntimeException("参数为空，数据无法路由。");
        }
        if (obj instanceof SvcBookingOrder) {
            return ((SvcBookingOrder) obj).getErpOrderId();
        }

        if (obj instanceof Map) {
            Object erpOrderId = ((Map) obj).get("erpOrderId");
            if (erpOrderId != null && erpOrderId instanceof Number) {
                return Long.valueOf(erpOrderId + "");
            }
        }
        throw new RuntimeException("未知的参数类型！");
    }

    /**
     * 对三个数据库进行散列分布
     * 1、返回其他值，没有在配置文件中配置的，如负数等，在默认数据库中查找
     * 2、比如现在配置文件中配置有三个结果进行散列，如果返回为0，那么apply方法只调用一次，如果返回为2，
     * 那么apply方法就会被调用三次，也就是每次是按照配置文件的顺序依次的调用方法进行判断结果，而不会缓存方法返回值进行判断
     *
     * @param shardingId
     * @return
     */
    public int shardingDB(Long shardingId) {
        Long result =  Math.abs( (shardingId % DB_NUM));
        logger.debug("shardingId:{},分第【{}】库", shardingId, result);
        return result.intValue();
    }


}