package com.netease.okr.redis;

import com.netease.okr.util.LoggerUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisClientTest {
	
	private static JedisPool jedisPool;
	
	private static JedisPool pool;
    private static String host = "10.164.96.17";
    private static int port = 26903;
    private static int timeout = 60 * 1000;

    private static int maxActive = 100;
    private static int maxIdle = 20;
    private static long maxWait = 1000;
    
    private static String password = "";

    public static final int EXPIRE_TIME = 86400;

	public static void setJedisPool(JedisPool jedisPool) {
		RedisClientTest.jedisPool = jedisPool;
	}


	private static org.slf4j.Logger logger_ = org.slf4j.LoggerFactory
            .getLogger(RedisClient.class.getName());

    public static void initPool() {
        logger_.info("Init Redis Pool [{}]:[{}]", host, port);
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		config.setTestWhileIdle(true);
        
        pool = new JedisPool(config, host, port, timeout,password);// 线程数量限制，IP地址，端口，超时时间
    }

    public static Jedis getJedis() {
        if (pool == null)
            initPool();
        return pool.getResource();
    }
    
    
    public static String ping() {
        String result = null;

        Jedis jedis = getJedis();
        if (jedis == null) {
            return result;
        }

        try {
            result = jedis.ping();
        } catch (Exception e) {
            LoggerUtil.error(e.getMessage(), e);
        } finally {
            jedis.close();
        }

        return result;
    }
}
