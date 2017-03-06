package com.netease.okr.service;

import org.springframework.dao.DataAccessException;


public interface TaskLockService {

	/**
	 * 加锁
	 * 
	 * @param code
	 * @return
	 * @throws DataAccessException
	 */
	public boolean getTaskLock(String code) throws DataAccessException;

	/**
	 * 释放锁
	 * 
	 * @param code
	 * @throws DataAccessException
	 */
	public void releaseLock(String code) throws DataAccessException;

}
