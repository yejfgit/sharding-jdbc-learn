package com.netease.okr.mapper.okr;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;


public interface TaskLockMapper {

	/**
	 * 读取锁
	 * 
	 * @param code
	 * @return
	 * @throws DataAccessException
	 */
	public Integer peekLock(String code) throws DataAccessException;

	/**
	 * 更新职位同步锁
	 * 
	 * @param code
	 * @param lock
	 * @return
	 * @throws DataAccessException
	 */
	public Integer gainLock(@Param(value = "code") String code, @Param(value = "lock") Integer lock)
			throws DataAccessException;

	/**
	 * 释放锁
	 * 
	 * @param code
	 * @param lock
	 * @return
	 * @throws DataAccessException
	 */
	public Integer releaseLock(@Param(value = "code") String code) throws DataAccessException;

}
