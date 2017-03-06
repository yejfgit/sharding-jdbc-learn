package com.netease.okr.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.netease.okr.mapper.okr.TaskLockMapper;
import com.netease.okr.service.TaskLockService;
import com.netease.okr.util.LoggerUtil;


@Service
public class TaskLockServiceImpl implements TaskLockService {

	@Autowired
	private TaskLockMapper taskLockMapper;

	@Override
	public boolean getTaskLock(String code) throws DataAccessException {
		Integer lock = taskLockMapper.peekLock(code);
		LoggerUtil.info("获取定时任务锁[" + code + ":" + lock + "]");
		if (taskLockMapper.gainLock(code, lock) > 0) {
			LoggerUtil.info("加锁OK");
			return true;
		}
		LoggerUtil.info("加锁失败");
		return false;
	}

	/**
	 * 释放锁
	 * 
	 * @param code
	 * @param lock
	 * @throws DataAccessException
	 */
	@Override
	public void releaseLock(String code) throws DataAccessException {
		LoggerUtil.info("释放定时任务锁[" + code + "]");
		taskLockMapper.releaseLock(code);
	}

}
