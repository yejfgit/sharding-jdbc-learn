package com.netease.frame.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.okr.redis.RedisClient;
import com.netease.okr.redis.RedisClientTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext.xml","classpath:spring/applicationContext-database.xml","classpath:spring/applicationContext-redis.xml" })
public class RedisTest extends AbstractJUnit4SpringContextTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		try {
			System.out.println("---------------------------------"+RedisClientTest.ping()+"---------------------------------");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//RedisClient.set("1111", "redistest");
	}

}
