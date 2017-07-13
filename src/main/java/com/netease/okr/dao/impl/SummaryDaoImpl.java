package com.netease.okr.dao.impl;

import com.netease.okr.model.entity.Summary;
import com.netease.okr.dao.SummaryDao;
import com.netease.okr.mapper.okr.SummaryMapper;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * SummaryDaodao实现类
 * 
 **/

@Repository
public class SummaryDaoImpl extends SqlSessionDaoSupport implements SummaryDao {

	@Autowired
	private SummaryMapper summaryMapper;

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	@Override
	public Summary selectById(Integer id) {
		return summaryMapper.selectById(id);
	}

	@Override
	public List<Summary> getSummaryList(Summary summary) {
		return summaryMapper.getSummaryList(summary);
	}

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	@Override
	public int deleteById(Integer id) {
		return summaryMapper.deleteById(id);
	}

	/**
	 * 
	 * 添加
	 * 
	 **/
	@Override
	public int insertSummary(Summary summary) {
		return summaryMapper.insertSummary(summary);
	}
	@Override
	public int getSummaryCount(Integer userId) {
		return summaryMapper.getSummaryCount(userId);
	}
	
	
	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	@Override
	public int updateById(Summary summary) {
		return summaryMapper.updateById(summary);
	}
	
	
	@Override
	public int getSummaryCountOfDate(Integer dateTabId, Integer userId) {
		return summaryMapper.getSummaryCountOfDate(dateTabId, userId);
	}
}