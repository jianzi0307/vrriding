package com.pkuvr.game_server.redis;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * zset
 * 1、zadd：向名称为key的zset中添加元素member，score用于排序。如果该元素已经存在，则根据score更新该元素的顺序
 * 2、zrem：删除名称为key的zset中的元素member
 * 3、zincrby：如果在名称为key的zset中已经存在元素member，则该元素的score增加increment;否则向集合中添加该元素，其score的值为increment
 * 4、zrank：返回名称为key的zset中member元素的排名(按score从小到大排序)即下标
 * 5、zrevrank：返回名称为key的zset中member元素的排名(按score从大到小排序)即下标
 * 6、zrevrange：返回名称为key的zset(按score从大到小排序)中的index从start到end的所有元素
 * 7、zrangebyscore：返回集合中score在给定区间的元素
 * 8、zcount：返回集合中score在给定区间的数量
 * 9、zcard：返回集合中元素个数
 * 10、zscore：返回给定元素对应的score
 * 11、zremrangebyrank：删除集合中排名在给定区间的元素
 * 12、zremrangebyscore：删除集合中score在给定区间的元素
 *
 * @author SHACS
 */
public class BaseZSetCpt {
    private static final Logger logger = Logger.getLogger(BaseZSetCpt.class);
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
     * 保存数据到库中
     *
     * @param uid
     * @param score
     */
    public void saveOrUpdate(int uid, double score) {
        logger.debug("BaseZSetCpt.saveOrUpdate start");
        Jedis jedis = null;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 1.执行命令的主体
            jedis.zadd(key, score, String.valueOf(uid));
            logger.debug("BaseZSetCpt.save successful");
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.saveOrUpdate failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
    }

    /**
     * 删除所有列数据
     */
    public void removeAll() {
        logger.debug("BaseZSetCpt.removeAll start");
        Jedis jedis = null;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 1.执行命令的主体
            jedis.del(key);
            logger.debug("BaseZSetCpt.removeAll successful");
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.removeAll failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
    }


    /**
     * 增加指定uid的分数
     *
     * @param uid
     * @param score
     */
    public Double addScore(int uid, double addScore) {
        logger.debug("BaseZSetCpt.addScore start");
        Jedis jedis = null;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 1.执行命令的主体
            return jedis.zincrby(key, addScore, String.valueOf(uid));
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.addScore failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
        return null;
    }

    /**
     * 按照排名获取玩家id
     *
     * @param
     * @return
     */
    public List<Integer> get(long start, long end) {
        logger.debug("BaseZSetCpt.get start");
        Jedis jedis = null;

        List<Integer> rtnList = new ArrayList<Integer>();
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 2.执行命令的主体
            Set<String> rtnSet = jedis.zrevrange(key, start, end);
            if (rtnSet != null) {
                for (String rtn : rtnSet) {
                    rtnList.add(Integer.valueOf(rtn));
                }
            }
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.get failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
        return rtnList;
    }

    /**
     * 反向取出
     *
     * @param start
     * @param end
     * @return
     */
    public List<Integer> getDesc(long start, long end) {
        logger.debug("BaseZSetCpt.get start");
        Jedis jedis = null;

        List<Integer> rtnList = new ArrayList<Integer>();
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 2.执行命令的主体
            Set<String> rtnSet = jedis.zrange(key, start - 1, end - 1);
            if (rtnSet != null) {
                for (String rtn : rtnSet) {
                    rtnList.add(Integer.valueOf(rtn));
                }
            }
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.get failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
        return rtnList;
    }


    /**
     * 按照得分区间获取玩家id
     *
     * @param
     * @return
     */
    public List<Integer> getByScore(double start, double end) {
        logger.debug("BaseZSetCpt.get start");
        Jedis jedis = null;

        List<Integer> rtnList = new ArrayList<Integer>();
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 2.执行命令的主体
            Set<String> rtnSet = jedis.zrangeByScore(key, start, end);
            if (rtnSet != null) {
                for (String rtn : rtnSet) {
                    rtnList.add(Integer.valueOf(rtn));
                }
            }
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.get failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
        return rtnList;
    }

    /**
     * 按照玩家id删除记录
     *
     * @param
     * @return
     */
    public void deleteById(int uid) {
        logger.debug("BaseZSetCpt.getScore start");
        Jedis jedis = null;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 2.执行命令的主体
            jedis.zrem(key, String.valueOf(uid));
            logger.debug("BaseZSetCpt.delete successful");
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.getScore failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
    }

    /**
     * 按照玩家id获取分数
     *
     * @param
     * @return
     */
    public int getScore(int uid) {
        logger.debug("BaseZSetCpt.getScore start");
        Jedis jedis = null;
        Double score = null;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 2.执行命令的主体
            score = jedis.zscore(key, String.valueOf(uid));
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.getScore failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
        if (score == null)
            score = 0.0;
        return score.intValue();
    }

    /**
     * 按照玩家id获取排名
     *
     * @param
     * @return
     */
    public int getRank(int uid) {
        logger.debug("BaseZSetCpt.getRank start");
        Jedis jedis = null;
        Long rank = null;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 2.执行命令的主体
            rank = jedis.zrevrank(key, String.valueOf(uid));
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.getRank failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
        if (rank == null)
            rank = -1L;
        return rank.intValue();
    }


    public List<Integer> getAllRoles() {
        logger.debug("BaseZSetCpt.getAllRoles start");
        Jedis jedis = null;
        List<Integer> rtnList = new ArrayList<Integer>();
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 2.执行命令的主体
            Long num = jedis.zcard(key);
            Set<String> rtnSet = jedis.zrevrange(key, 0, num);
            if (rtnSet != null) {
                for (String rtn : rtnSet) {
                    rtnList.add(Integer.valueOf(rtn));
                }
            }
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.getRank failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
        return rtnList;
    }

    public long count() {
        logger.debug("BaseZSetCpt.getAllRoles start");
        Jedis jedis = null;
        Long num = 0L;
        try {
            // 0.获取连接并选中库
            jedis = jedisPoolLogin.getResource();
            if (dbId != jedis.getDB())
                jedis.select(dbId); // 这个命令也是需要消耗时间的,我们始终使用同一个库,所以能少发一条命令就少发一条命令

            // 2.执行命令的主体
            num = jedis.zcard(key);
        } catch (Exception e) {
            logger.error(explain + ",BaseZSetCpt.getRank failed", e);
            jedisPoolLogin.returnBrokenResource(jedis);
        } finally {
            jedisPoolLogin.returnResource(jedis);
        }
        return num;
    }

}




























