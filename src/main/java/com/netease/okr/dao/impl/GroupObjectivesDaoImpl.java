package com.netease.okr.dao.impl;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.GroupObjectivesDao;
import com.netease.okr.mapper.okr.GroupObjectivesMapper;
import com.netease.okr.model.entity.GroupObjectives;
import com.netease.okr.model.entity.GroupObjectivesRel;

@Repository
public class GroupObjectivesDaoImpl extends SqlSessionDaoSupport implements GroupObjectivesDao {
	
	@Autowired
	private GroupObjectivesMapper groupObjectivesMapper;

	@Override
	public List<GroupObjectives> getGroupObjectivesList(@Param(value = "deptL1Id") String deptL1Id) {
		return groupObjectivesMapper.getGroupObjectivesList(deptL1Id);
	}

	@Override
	public Integer addGroupObjectives(GroupObjectives groupObjectives) {
		return groupObjectivesMapper.addGroupObjectives(groupObjectives);
	}
	@Override
	public Integer getObjectivesList(Integer id) {
		return groupObjectivesMapper.getObjectivesList(id);
	}
	
	@Override
	public Integer deleteGroupObjectives(Integer id)  {
		return groupObjectivesMapper.deleteGroupObjectives(id);
	}
	
	@Override
	public Integer getNextCodeNum(@Param(value = "deptL1Id") String deptL1Id)  {
		return groupObjectivesMapper.getNextCodeNum(deptL1Id);
	}
	
	@Override
	public Integer updateGroupObjectives(GroupObjectives groupObjectives)  {
		return groupObjectivesMapper.updateGroupObjectives(groupObjectives);
	}
	
	@Override
	public Integer addGroupObjectivesRel(@Param(value = "list") List<GroupObjectivesRel> list) {
		return groupObjectivesMapper.addGroupObjectivesRel(list);
	}
	
	@Override
	public Integer deleteGroupObjectivesRel(@Param(value = "groupObjectivesId") Integer groupObjectivesId) {
		return groupObjectivesMapper.deleteGroupObjectivesRel(groupObjectivesId);
	}
	
}
