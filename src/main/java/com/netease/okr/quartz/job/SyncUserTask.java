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
public class SyncUserTask implements JobProcessor {

	@Autowired
	private EhrDateService ehrDateService;
	
	@Autowired
	private TaskLockService taskLockService;
	
	@Scheduled(cron = "0 0/5 * * * ?")
	/*@Scheduled(cron = "0 50 23 * * ?")*/
	public void handle() {
		LoggerUtil.info("SyncUserTask--begin");
		
		if (!taskLockService.getTaskLock(TaskTypeEnum.USER_SYNC.getName())) {
			LoggerUtil.info(TaskTypeEnum.USER_SYNC.getRemark()+"获取任务锁失败，退出任务");
			return;
		}
		try {
			ehrDateService.syncUser();
		} catch (Exception e) {
			LoggerUtil.error("同步用户异常", e);
		}finally{
			// 释放锁
			taskLockService.releaseLock(TaskTypeEnum.USER_SYNC.getName());
		}
		
		LoggerUtil.info("SyncUserTask--end");
		
	}

	/**
	 * 手动触发定时任务
	 * */
	@Override
	public void execute(List<Integer> ids, Integer userId) {
		LoggerUtil.info("SyncUserTask--begin");
		ehrDateService.syncUser();
		LoggerUtil.info("SyncUserTask--end");
	}
	
	

}
