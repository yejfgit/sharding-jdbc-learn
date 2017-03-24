package com.netease.frame.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.okr.redis.RedisClient;


/**
 * @author yejf
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext.xml","classpath:spring/applicationContext-database.xml","classpath:spring/applicationContext-redis.xml" })
public class RedisTest extends AbstractJUnit4SpringContextTests {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test2() {
		try {
			System.out.println("---------------------------------"+RedisClient.ping()+"---------------------------------");
			System.out.println("---------------------------------"+RedisClient.set("test012", "adsfasdfasdfasdfasdfasdfasdf")+"---------------------------------");
			System.out.println("---------------------------------"+RedisClient.get("test012")+"---------------------------------");
			
			
			System.out.println("*******************************sort set test************************************************");
			
			RedisClient.zadd("t1", 1, "1");
			RedisClient.zadd("t1", 3, "3");
			RedisClient.zadd("t1", 2, "2");
			RedisClient.zadd("t1", 4, "4");
			
			Set<String> set = RedisClient.zrevrange("t1", 0, -1);//倒序
			Set<String> set2 = RedisClient.zrange("t1", 0, -1);//倒序
			
	        Iterator<String> iter = set.iterator();
	        
	        while(iter.hasNext()) {  
	        	System.out.println("---------------------------------"+iter.next()+"---------------------------------");
	        }
	        
	        
	        Iterator<String> iter2 = set2.iterator();
	        
	        while(iter2.hasNext()) {  
	        	System.out.println("---------------------------------"+iter2.next()+"---------------------------------");
	        }
			
	        
	        System.out.println("******************************list test*************************************************");
	        
	        RedisClient.rpush("t2", "rpushtest2");
	        RedisClient.rpush("t2", "rpushtest3");
	        RedisClient.rpush("t2", "rpushtest5");
	        RedisClient.rpush("t2", "rpushtest4");
	        
	        List<String> t2t = RedisClient.lrange("t2", 0, -1);
	        
	        for(String str:t2t){
	        	System.out.println("---------------------------------"+str+"---------------------------------");
	        }
	        
	        
	        
	        System.out.println("******************************对存储结构为Set（集合）类型的操作*************************************************");
	        
	        
	        RedisClient.sadd("Set3", "Settest2");
	        RedisClient.sadd("Set3", "Settest3");
	        RedisClient.sadd("Set3", "Settest5");
	        RedisClient.sadd("Set3", "Settest4");
	        
	        Set<String> set3 = RedisClient.smembers("Set3");

	        Iterator<String> iter3 = set3.iterator();
	        
	        while(iter3.hasNext()) {  
	        	System.out.println("---------------------------------"+iter3.next()+"---------------------------------");
	        }
	        
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//RedisClient.set("1111", "redistest");
	}
	
	/*@Test
	public void test() {
		Jedis jedis = null;
		jedis = jedisSentinel.getSentinelPool().getResource();
		//jedis.select(RDS_DB_APL);
		
		LoggerUtil.info("-------------------S0:qryps.temp-----------------------------");
		
		jedis.set("adfasdfas", "asdfasdfasdfasdfasd");
		System.out.println("---------------------------------"+jedis.get("adfasdfas")+"---------------------------------");
		Set<String> d15 = jedis.keys("S0:qryps.temp*");
		if (d15 != null && d15.size() > 0) {
			for (String d : d15)
				jedis.del(d);
		}

	}*/
	
	
}
