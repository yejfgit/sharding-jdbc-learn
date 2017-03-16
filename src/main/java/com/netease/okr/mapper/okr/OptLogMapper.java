package com.netease.okr.mapper.okr;

import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.OptLog;


/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface OptLogMapper {
	
	/**
	 * 添加时间信息
	 * @return
	 * @throws DataAccessException
	 */
	public Integer addOptLog(OptLog optLog)  throws DataAccessException;
	
}
