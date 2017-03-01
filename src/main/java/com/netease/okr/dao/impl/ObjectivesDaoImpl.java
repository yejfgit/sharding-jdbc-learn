package com.netease.okr.dao.impl;


import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.ObjectivesDao;
import com.netease.okr.mapper.okr.ObjectivesMapper;
import com.netease.okr.model.entity.Objectives;

@Repository
public class ObjectivesDaoImpl extends SqlSessionDaoSupport implements ObjectivesDao {
	
	@Autowired
	private ObjectivesMapper objectivesMapper;
	
	
	/**
	 * @author yejf
	 * @param userId
	 * @return List<Objectives>
	 * @throws DataAccessException
	 */
	@Override
	public List<Objectives> getMyOkrList(Integer userId) {
		return objectivesMapper.getMyOkrList(userId);
	}
	
	@Override
	public Objectives getObjectivesById(Integer id) {
		return objectivesMapper.getObjectivesById(id);
	}
	
	
	
	@Override
	public Integer getAllMyOkrListCount(Integer userId){
		return objectivesMapper.getAllMyOkrListCount(userId);
	}
	
	
	@Override
	public Integer addObjectives(Objectives objectives){
		return objectivesMapper.addObjectives(objectives);
	}
	
	@Override
	public Integer deleteObjectives(Integer id){
		return objectivesMapper.deleteObjectives(id);
	}
	
	@Override
	public Integer updateObjectives(Objectives objectives){
		return objectivesMapper.updateObjectives(objectives);
	}
}
