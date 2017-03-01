package com.netease.okr.quartz.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.netease.okr.service.EhrDateService;
import com.netease.okr.util.LoggerUtil;

/**
 * hzyejinfu 
 *
 */
@Component
public class SyncDeptTask implements JobProcessor {

	@Autowired
	private EhrDateService ehrDateService;
	
	
	/*@Scheduled(cron = "0 0/10 * * * ?")*/
	@Scheduled(cron = "0 50 12,23 * * ?")
	public void handle() {
		LoggerUtil.info("SyncDeptTask--begin");
		ehrDateService.syncDept();
		LoggerUtil.info("SyncDeptTask--end");
		
	}

	/**
	 * 手动触发定时任务
	 * */
	@Override
	public void execute(List<Integer> ids, Integer userId) {
		LoggerUtil.info("SyncDeptTask--begin");
		ehrDateService.syncDept();
		LoggerUtil.info("SyncDeptTask--end");
	}
	

}
