package com.raines.raineslearn.redis.nx;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisLockUtils {

    // Jedispool
    JedisCommands jedisCommands;
    JedisPool jedisPool;
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    String ip = "172.16.100.4";
    int port = 6379;
    int timeout = 2000;

    public RedisLockUtils() {
        // 初始化jedis
        // 设置配置
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(100);
        jedisPoolConfig.setTestOnBorrow(false);//jedis 第一次启动时，会报错
        jedisPoolConfig.setTestOnReturn(true);
        // 初始化JedisPool
        jedisPool = new JedisPool(jedisPoolConfig, ip, port, timeout);

//        Jedis jedis = jedisPool.getResource();
//        jedisCommands = jedis;
    }

    /**
     * 这里注意点在于jedis的set方法，其参数的说明如：
     * NX：是否存在key，存在就不set成功
     * PX：key过期时间单位设置为毫秒（EX：单位秒）
     * @param key
     * @param val
     * @return
     */
    public boolean setnx(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) {
                return false;
            }
            return jedis.set(key, val, "NX", "PX", 10000 * 60).
                    equalsIgnoreCase("ok");
        } catch (NullPointerException ex) {
        } catch (Exception ex) {
            System.out.println("错误+++");
            ex.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public int delnx(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) {
                return 0;
            }

            //if redis.call('get','orderkey')=='1111' then return redis.call('del','orderkey') else return 0 end
            StringBuilder sbScript = new StringBuilder();
            sbScript.append("if redis.call('get','").append(key).append("')").append("=='").append(val).append("'").
                    append(" then ").
                    append(" return redis.call('del','").append(key).append("')").
                    append(" else ").
                    append(" return 0").
                    append(" end");

            return Integer.valueOf(jedis.eval(sbScript.toString()).toString());
        } catch (Exception ex) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }


}
