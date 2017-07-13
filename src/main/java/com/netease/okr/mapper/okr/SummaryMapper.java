package com.netease.okr.mapper.okr;

import com.netease.okr.model.entity.Summary;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

/**
 * 
 * SummaryMapper数据库操作接口类
 * 
 **/

public interface SummaryMapper {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	public Summary selectById(@Param("id") Integer id) throws DataAccessException;

	public List<Summary> getSummaryList(Summary summary) throws DataAccessException;

	public int getSummaryCountOfDate(@Param("dateTabId") Integer dateTabId, @Param("userId") Integer userId)
			throws DataAccessException;

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	public int deleteById(@Param("id") Integer id) throws DataAccessException;
	
	public int getSummaryCount(@Param("userId") Integer userId) throws DataAccessException;
	
	
	/**
	 * 
	 * 添加
	 * 
	 **/
	public int insertSummary(Summary record) throws DataAccessException;

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	public int updateById(Summary record) throws DataAccessException;

}