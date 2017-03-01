package com.netease.okr.mapper.okr;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.DateInfo;



/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface DateInfoMapper {
	
	/**
	 * 添加时间信息
	 * @return
	 * @throws DataAccessException
	 */
	public Integer addDateInfo(DateInfo dateInfo)  throws DataAccessException;
	
	
	/**
	 * 时间信息
	 * @param parentId
	 * @returnList<DateInfo>
	 * @throws DataAccessException
	 */
	public List<DateInfo> getAllDateInfo()  throws DataAccessException;
	
	
	/**
	 * 获取月份信息
	 * @return
	 * @throws DataAccessException
	 */
	public String getMonth(DateInfo dateInfo)  throws DataAccessException;
	
}
