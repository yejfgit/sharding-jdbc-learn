package com.netease.okr.dao.impl;

import com.netease.okr.model.entity.SummaryOther;
import com.netease.okr.dao.SummaryOtherDao;
import com.netease.okr.mapper.okr.SummaryOtherMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * SummaryOtherDaodao实现类
 * 
 **/

@Repository
public class SummaryOtherDaoImpl extends SqlSessionDaoSupport implements SummaryOtherDao{

	 @Autowired
	 private SummaryOtherMapper summaryOtherMapper;

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	 @Override
	 public SummaryOther  selectById (Integer id){
		 return summaryOtherMapper.selectById(id);
	 }
	 
	 @Override
	 public List<SummaryOther>  getSummaryOtherList (Integer summaryId){
		return  summaryOtherMapper.getSummaryOtherList(summaryId);
	 }
	 
	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	 @Override
	 public int deleteById (Integer id){
		 return summaryOtherMapper.deleteById(id);
	 }

	/**
	 * 
	 * 添加
	 * 
	 **/
	 @Override
	 public int insertSummaryOther(SummaryOther summaryOther) {
		 return summaryOtherMapper.insertSummaryOther(summaryOther);
	 }
	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	 @Override
	 public int updateById (SummaryOther summaryOther) {
		 return summaryOtherMapper.updateById(summaryOther);
	 }
}