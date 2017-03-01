package com.netease.okr.quartz.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.netease.okr.service.DateInfoService;
import com.netease.okr.util.LoggerUtil;

/**
 * hzyejinfu 
 *
 */
@Component
public class CreateDateTask{

	@Autowired
	private DateInfoService dateInfoService;
	
	/*@Scheduled(cron = "0 0/2 * * * ?")
	public void handle() {
		LoggerUtil.info("CreateDateTask--begin");
		dateInfoService.createDateInfo("2017-12-31","2019-01-01");
		LoggerUtil.info("CreateDateTask--end");
		
	}*/


}
