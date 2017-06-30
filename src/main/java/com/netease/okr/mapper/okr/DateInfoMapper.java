package com.netease.okr.mapper.okr;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	
	
	public List<DateInfo> getCurDateInfo(Date curDate)  throws DataAccessException;
	
	
	/**
	 * 获取月份信息
	 * @return
	 * @throws DataAccessException
	 */
	public String getMonth(DateInfo dateInfo)  throws DataAccessException;
	
	/**
	 * 获取时间id
	 * @return
	 * @throws DataAccessException
	 */
	public Integer getDateId(DateInfo dateInfo)  throws DataAccessException;
	
	
	public List<DateInfo> getClassDateByModel(@Param(value = "type") Integer type)  throws DataAccessException;
	
	public DateInfo getClassDateById(@Param(value = "id") Integer id)  throws DataAccessException;
	
	
	
	
}
