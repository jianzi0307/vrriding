package com.pkuvr.game_server.redis;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * SessionKey 专用库,不论用登陆哪个游戏,都使用同一个 SessionKey 库
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
public class SessionKeyHMapCpt {
    private static final Logger logger = Logger.getLogger(SessionKeyHMapCpt.class);
    private String explain = "临时变量列"; // 用途说明
    private int dbId = 0; // 使用的数据库id
    private String key = "tmp"; // 列的Key
    @Resource
    private JedisPool jedisPoolLogin;

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
     * 从库中获取 SessionKey
     *
     * @param roleId 玩家id
     * @return 如果未登录, 则返回null
     */
    public String getSessionKey(int roleId) {
        logger.debug("SessionKeyHMapCpt.getSessionKey start");
        Jedis jedis = null;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 1.执行命令的主体
//			return jedis.hget(key, String.valueOf(roleId));
            return jedis.get(String.valueOf(roleId));
        } catch (Exception e) {
            logger.error(explain + ",SessionKeyHMapCpt.getSessionKey failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
        return null;
    }

    public void remove(int roleId) {
        logger.debug("SessionKeyHMapCpt.remove start");
        Jedis jedis = null;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 1.执行命令的主体
            jedis.hdel(key, String.valueOf(roleId));
        } catch (Exception e) {
            logger.error(explain + ",SessionKeyHMapCpt.remove failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
    }
}




























