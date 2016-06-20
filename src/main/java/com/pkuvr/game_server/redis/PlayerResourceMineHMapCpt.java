package com.pkuvr.game_server.redis;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * PlayerResource 专用库
 * 1、HDEL：删除指定key的hash表的值
 * 2、HEXISTS：判断指定的key在hash表中是否存在
 * 3、HGET：从hash表中获取指定key的值
 * 4、HGETALL：获取hash中所有的元素,包括key和value
 * 5、HINCRBY：增加hash表中指定key的value的值,(可加可减),value会变的
 * 6、HKEYS：返回hash表中所有的Key
 * 7、HLEN：返回hash表中的记录数
 * 8、HMGET：一次获取hash中的多个值
 * 9、HMSET：一次设定hash表中的多个key-value对
 * 10、HSET：设定hash表中指定的key-valve对
 * 11、HSETNX：设定hash表中指定的key-valve对,当且仅当key不存在时生效
 * 12、HVALS：获取hash表中所有的value
 *
 * @author SHACS
 */
public class PlayerResourceMineHMapCpt {
    private static final Logger logger = Logger.getLogger(PlayerResourceMineHMapCpt.class);
    private String explain = "临时变量列"; // 用途说明
    private int dbId = 0; // 使用的数据库id
    private String key = "tmp"; // 列的Key
    @Resource
    private JedisPool jedisPoolDefault;

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 保存数据到库中
     *
     * @param uid
     * @param score
     */
    public void saveOrUpdate(long resMineInstanceId, int roleId) {
        logger.debug("PlayerResourceMineHMapCpt.save start");
        Jedis jedis = null;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolDefault.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 1.执行命令的主体
            jedis.zadd(key, roleId, String.valueOf(resMineInstanceId));
            logger.debug("PlayerResourceMineHMapCpt.save successful");
        } catch (Exception e) {
            logger.error(explain + ",PlayerResourceMineHMapCpt.save failed", e);
            jedisPoolDefault.returnBrokenResource(jedis);
        } finally {
            jedisPoolDefault.returnResource(jedis);
        }
    }

    /**
     * 从库中获取roleId
     *
     * @param resMineInstanceId 玩家资源点id
     * @return
     */
    public int getMineRoleId(long resMineInstanceId) {
        logger.debug("PlayerResourceMineHMapCpt.mineinfo start");
        Jedis jedis = null;
        Double score = null;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolDefault.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 1.执行命令的主体
            score = jedis.zscore(key, String.valueOf(resMineInstanceId));
        } catch (Exception e) {
            logger.error(explain + ",PlayerResourceMineHMapCpt.mineinfo failed", e);
            jedisPoolDefault.returnBrokenResource(jedis);
        } finally {
            jedisPoolDefault.returnResource(jedis);
        }
        if (score == null)
            score = 0.0;
        return score.intValue();
    }
}
