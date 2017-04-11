/*package com.netease.okr.quartz.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.netease.okr.Lucence.UserIndexServer;
import com.netease.okr.enums.TaskTypeEnum;
import com.netease.okr.service.TaskLockService;
import com.netease.okr.util.LoggerUtil;

*//**
 * yejf 
 *
 *//*
@Component
public class BuildUserIndexTask {

	@Autowired
	private UserIndexServer userIndexServer;
	
	@Autowired
	private TaskLockService taskLockService;
	
	@Scheduled(cron = "0 30 2 * * ?")
	public void handle() {
		LoggerUtil.info("BuildUserIndexTask--begin");
		
		if (!taskLockService.getTaskLock(TaskTypeEnum.USER_INDEX.getName())) {
			LoggerUtil.info(TaskTypeEnum.USER_INDEX.getRemark()+"获取任务锁失败，退出任务");
			return;
		}
		try {
			userIndexServer.buildUserIndex();
		} catch (Exception e) {
			LoggerUtil.error("创建用户索引异常", e);
		}finally{
			// 释放锁
			taskLockService.releaseLock(TaskTypeEnum.USER_INDEX.getName());
		}
		
		LoggerUtil.info("BuildUserIndexTask--end");
		
	}
	
	

}
*/