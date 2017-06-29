package com.netease.okr.mapper.okr;

import com.netease.okr.model.entity.SummaryOther;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

/**
 * 
 * SummaryOtherMapper数据库操作接口类
 * 
 **/

public interface SummaryOtherMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	 public SummaryOther  selectById (@Param("id") Integer id) throws DataAccessException;
	 
	 public List<SummaryOther>  getSummaryOtherList (@Param("summaryId") Integer summaryId) throws DataAccessException;
	 
	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	 public int deleteById (@Param("id") Integer id)  throws DataAccessException;

	/**
	 * 
	 * 添加
	 * 
	 **/
	 public int insertSummaryOther(SummaryOther record)  throws DataAccessException;

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	 public int updateById (SummaryOther record)  throws DataAccessException;

}