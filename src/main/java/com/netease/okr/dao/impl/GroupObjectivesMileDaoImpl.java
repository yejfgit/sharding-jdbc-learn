package com.netease.okr.dao.impl;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.GroupObjectivesMileDao;
import com.netease.okr.mapper.okr.GroupObjectivesMileMapper;
import com.netease.okr.model.entity.GroupObjectivesMile;

@Repository
public class GroupObjectivesMileDaoImpl extends SqlSessionDaoSupport implements GroupObjectivesMileDao {
	
	@Autowired
	private GroupObjectivesMileMapper groupObjectivesMileMapper;

	@Override
	public List<GroupObjectivesMile> getGroupObjectivesMiles(@Param(value = "groupObjectivesId") Integer groupObjectivesId) {
		return groupObjectivesMileMapper.getGroupObjectivesMiles(groupObjectivesId);
	}

	@Override
	public Integer addGroupObjectivesMile(GroupObjectivesMile groupObjectivesMile) {
		return groupObjectivesMileMapper.addGroupObjectivesMile(groupObjectivesMile);
	}
	
	@Override
	public Integer deleteGroupObjectivesMileById(Integer id) {
		return groupObjectivesMileMapper.deleteGroupObjectivesMileById(id);
	}
	
	@Override
	public Integer deleteGroupObjectivesMile(Integer groupObjectivesId) {
		return groupObjectivesMileMapper.deleteGroupObjectivesMile(groupObjectivesId);
	}
	
	@Override
	public Integer updateGroupObjectivesMile(GroupObjectivesMile groupObjectivesMile) {
		return groupObjectivesMileMapper.updateGroupObjectivesMile(groupObjectivesMile);
	}
}
