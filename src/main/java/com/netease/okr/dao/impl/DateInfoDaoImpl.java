package com.netease.okr.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.DateInfoDao;
import com.netease.okr.mapper.okr.DateInfoMapper;
import com.netease.okr.model.entity.DateInfo;

@Repository
public class DateInfoDaoImpl extends SqlSessionDaoSupport implements DateInfoDao {
	
	@Autowired
	private DateInfoMapper dateInfoMapper;
	
	@Override
	public Integer addDateInfo(DateInfo dateInfo){
		return dateInfoMapper.addDateInfo(dateInfo);
	}
	
	
	@Override
	public List<DateInfo> getAllDateInfo(){
		return dateInfoMapper.getAllDateInfo();
	}
	
}
