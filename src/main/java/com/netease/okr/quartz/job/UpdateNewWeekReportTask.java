package com.netease.okr.quartz.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.netease.okr.enums.TaskTypeEnum;
import com.netease.okr.service.TaskLockService;
import com.netease.okr.service.UserService;
import com.netease.okr.util.LoggerUtil;

/**
 * hzyejinfu 
 *更新用户最新周报信息
 */
@Component
public class UpdateNewWeekReportTask  implements JobProcessor {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskLockService taskLockService;
	
	@Scheduled(cron = "0 0/30 * * * ?")
	public void handle() {
		LoggerUtil.info("UpdateNewWeekReportTask--begin");
		
		if (!taskLockService.getTaskLock(TaskTypeEnum.USER_NEW_WEEK_REPORT.getName())) {
			LoggerUtil.info(TaskTypeEnum.USER_NEW_WEEK_REPORT.getRemark()+"获取任务锁失败，退出任务");
			return;
		}
		try {
			userService.updateUserNewWeekReport();
		} catch (Exception e) {
			LoggerUtil.error("同步用户异常", e);
		}finally{
			// 释放锁
			taskLockService.releaseLock(TaskTypeEnum.USER_NEW_WEEK_REPORT.getName());
		}
		
		LoggerUtil.info("UpdateNewWeekReportTask--end");
		
	}
	
	
	/**
	 * 删除周报更新最新周报任务
	 * 手动触发定时任务
	 * */
	@Override
	public void execute(List<Integer> ids, Integer userId) {
		LoggerUtil.info("UpdateNewWeekReportTask--begin");
		
		if(userId!=null){
			userService.updateUserNewWeekReport(userId);
		}
		
		LoggerUtil.info("UpdateNewWeekReportTask--end");
	}


}
