package com.netease.frame.test;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.okr.redis.JedisSentinel;
import com.netease.okr.redis.RedisClient;
import com.netease.okr.redis.RedisClientTest;
import com.netease.okr.util.LoggerUtil;

import redis.clients.jedis.Jedis;


/**
 * @author yejf
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext.xml","classpath:spring/applicationContext-database.xml","classpath:spring/applicationContext-redis.xml" })
public class RedisTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private JedisSentinel jedisSentinel;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test2() {
		try {
			//System.out.println("---------------------------------"+RedisClient.ping()+"---------------------------------");
			//System.out.println("---------------------------------"+RedisClient.set("test012", "adsfasdfasdfasdfasdfasdfasdf")+"---------------------------------");
			//System.out.println("---------------------------------"+RedisClient.get("test012")+"---------------------------------");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//RedisClient.set("1111", "redistest");
	}
	
	@Test
	public void test() {
		Jedis jedis = null;
		jedis = jedisSentinel.getSentinelPool().getResource();
		//jedis.select(RDS_DB_APL);
		
		LoggerUtil.info("-------------------S0:qryps.temp-----------------------------");
		
		jedis.set("adfasdfas", "asdfasdfasdfasdfasd");
		System.out.println("---------------------------------"+jedis.get("adfasdfas")+"---------------------------------");
		/*Set<String> d15 = jedis.keys("S0:qryps.temp*");
		if (d15 != null && d15.size() > 0) {
			for (String d : d15)
				jedis.del(d);
		}*/

	}
	
	
}
