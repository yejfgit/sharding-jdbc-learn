package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.SummaryOther;

/**
 * 
 * SummaryOtherDaodao类
 * 
 **/

public interface SummaryOtherDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	public SummaryOther selectById(Integer id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	public int deleteById(Integer id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	public int insertSummaryOther(SummaryOther summaryOther);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	public int updateById(SummaryOther summaryOther);

	public List<SummaryOther> getSummaryOtherList(Integer summaryId);

	public int deleteBySummaryId(Integer summaryId);

}