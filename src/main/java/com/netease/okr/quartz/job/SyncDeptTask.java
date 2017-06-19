package com.netease.okr.quartz.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.netease.okr.enums.TaskTypeEnum;
import com.netease.okr.service.EhrDateService;
import com.netease.okr.service.TaskLockService;
import com.netease.okr.util.LoggerUtil;

/**
 * yejf 
 *
 */
@Component
public class SyncDeptTask {

	@Autowired
	private EhrDateService ehrDateService;
	
	@Autowired
	private TaskLockService taskLockService;
	
	
	@Scheduled(cron = "0 50 12,1 * * ?")
	public void handle() {
		LoggerUtil.info("SyncDeptTask--begin");

		if (!taskLockService.getTaskLock(TaskTypeEnum.DEPT_SYNC.getName())) {
			LoggerUtil.info(TaskTypeEnum.DEPT_SYNC.getRemark()+"获取任务锁失败，退出任务");
			return;
		}
		
		try {
			ehrDateService.syncDept();
		} catch (Exception e) {
			LoggerUtil.error("同步用户异常", e);
		}finally{
			// 释放锁
			taskLockService.releaseLock(TaskTypeEnum.DEPT_SYNC.getName());
		}
		
		LoggerUtil.info("SyncDeptTask--end");
	}
	
	

}
