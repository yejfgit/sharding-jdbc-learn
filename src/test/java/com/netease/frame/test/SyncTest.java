package com.netease.frame.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.netease.okr.service.EhrDateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/applicationContext.xml","classpath:spring/springMVC.xml","classpath:spring/applicationContext-database.xml" })
public class SyncTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private EhrDateService ehrDateService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		//ehrDateService.syncDept();
		ehrDateService.syncUser();
	}

}
