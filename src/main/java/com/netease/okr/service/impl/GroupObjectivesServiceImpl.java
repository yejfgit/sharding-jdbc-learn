package com.netease.okr.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.GroupObjectivesDao;
import com.netease.okr.model.entity.GroupObjectives;
import com.netease.okr.model.entity.GroupObjectivesRel;
import com.netease.okr.model.entity.GroupObjectivesRelList;
import com.netease.okr.service.GroupObjectivesService;

@Repository
public class GroupObjectivesServiceImpl extends SqlSessionDaoSupport implements GroupObjectivesService {
	
	@Autowired
	private GroupObjectivesDao groupObjectivesDao;

	@Override
	public List<GroupObjectives> getGroupObjectivesList(@Param(value = "deptL1Id") String deptL1Id) {
		return groupObjectivesDao.getGroupObjectivesList(deptL1Id);
	}

	@Override
	public Integer addGroupObjectives(GroupObjectives groupObjectives) {
		return groupObjectivesDao.addGroupObjectives(groupObjectives);
	}
	
	@Override
	public Integer deleteGroupObjectives(Integer id)  {
		return groupObjectivesDao.deleteGroupObjectives(id);
	}
	
	@Override
	public Integer getNextCodeNum(@Param(value = "deptL1Id") String deptL1Id)  {
		return groupObjectivesDao.getNextCodeNum(deptL1Id);
	}
	
	@Override
	public Integer updateGroupObjectives(GroupObjectives groupObjectives)  {
		return groupObjectivesDao.updateGroupObjectives(groupObjectives);
	}
	
	@Override
	public Integer addGroupObjectivesRel(GroupObjectivesRelList groupObjectivesRelList) {
		
		List<GroupObjectivesRel> goRelList = new ArrayList<GroupObjectivesRel>();
		Integer groupObjectivesId = groupObjectivesRelList.getGroupObjectivesId();
		
		List<Integer> objectivesIds = groupObjectivesRelList.getObjectivesIdList();
		for(Integer objectivesId:objectivesIds){
			GroupObjectivesRel gol = new GroupObjectivesRel();
			gol.setObjectivesId(objectivesId);
			gol.setGroupObjectivesId(groupObjectivesId);
			goRelList.add(gol);
		}
		
		groupObjectivesDao.deleteGroupObjectivesRel(groupObjectivesId);
		return groupObjectivesDao.addGroupObjectivesRel(goRelList);
	}
	
	@Override
	public Integer getObjectivesList(Integer id) {
		return groupObjectivesDao.getObjectivesList(id);
	}
	
	@Override
	public Integer deleteGroupObjectivesRel(@Param(value = "groupObjectivesId") Integer groupObjectivesId) {
		return groupObjectivesDao.deleteGroupObjectivesRel(groupObjectivesId);
	}
	
}
