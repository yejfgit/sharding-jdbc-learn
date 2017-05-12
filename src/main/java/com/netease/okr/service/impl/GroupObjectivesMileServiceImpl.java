package com.netease.okr.service.impl;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.GroupObjectivesMileDao;
import com.netease.okr.model.entity.GroupObjectivesMile;
import com.netease.okr.service.GroupObjectivesMileService;

@Repository
public class GroupObjectivesMileServiceImpl extends SqlSessionDaoSupport implements GroupObjectivesMileService {
	
	@Autowired
	private GroupObjectivesMileDao groupObjectivesMileDao;

	@Override
	public List<GroupObjectivesMile> getGroupObjectivesMiles(@Param(value = "groupObjectivesId") Integer groupObjectivesId) {
		return groupObjectivesMileDao.getGroupObjectivesMiles(groupObjectivesId);
	}

	@Override
	public Integer addGroupObjectivesMile(GroupObjectivesMile groupObjectivesMile) {
		return groupObjectivesMileDao.addGroupObjectivesMile(groupObjectivesMile);
	}
	
	@Override
	public Integer deleteGroupObjectivesMileById(Integer id) {
		return groupObjectivesMileDao.deleteGroupObjectivesMileById(id);
	}
	
	@Override
	public Integer deleteGroupObjectivesMile( Integer groupObjectivesId) {
		return groupObjectivesMileDao.deleteGroupObjectivesMile(groupObjectivesId);
	}
	
	@Override
	public Integer updateGroupObjectivesMile(GroupObjectivesMile groupObjectivesMile) {
		return groupObjectivesMileDao.updateGroupObjectivesMile(groupObjectivesMile);
	}
}
