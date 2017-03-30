package com.netease.frame.test.apollo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.okr.mq.MqApolloClient;


/**
 * @author yejf
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext.xml","classpath:spring/applicationContext-mq.xml","classpath:spring/applicationContext-database.xml" })
public class MqClientTest extends AbstractJUnit4SpringContextTests {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		MqApolloClient.sendMessage("chat/general", "MqApolloClient test");
		MqApolloClient.sendMessage("chat/general1", "MqApolloClient test2");
		MqApolloClient.sendMessage("chat/general2", "MqApolloClient test3");

	}
	
	
}
