package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.Summary;

/**
 * 
 * SummaryDaodao类
 * 
 **/

 public interface SummaryDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	 public Summary  selectById (Integer id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	 public int deleteById (Integer id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	 public int insertSummary( Summary summary );

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	 public int updateById ( Summary summary );

	 public List<Summary> getSummaryList(Integer userId); 

}