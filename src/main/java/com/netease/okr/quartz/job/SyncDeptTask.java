package com.netease.okr.quartz.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.netease.okr.enums.TaskTypeEnum;
import com.netease.okr.service.EhrDateService;
import com.netease.okr.service.TaskLockService;
import com.netease.okr.util.LoggerUtil;

/**
 * hzyejinfu 
 *
 */
@Component
public class SyncDeptTask implements JobProcessor {

	@Autowired
	private EhrDateService ehrDateService;
	
	@Autowired
	private TaskLockService taskLockService;
	
	
	@Scheduled(cron = "0 50 12,23 * * ?")
	public void handle() {
		LoggerUtil.info("SyncDeptTask--begin");

		if (!taskLockService.getTaskLock(TaskTypeEnum.DEPT_SYNC.getName())) {
			LoggerUtil.info("1获取任务锁失败，退出任务");
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
