package com.netease.okr.listener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.netease.okr.util.LoggerUtil;


public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		String rootPath = servletContext.getRealPath("/");
		System.setProperty("REAL_PATH", rootPath);
		LoggerUtil.info("System.getProperty('REAL_PATH')====>" + rootPath);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}