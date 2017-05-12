package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.GroupObjectives;
import com.netease.okr.model.entity.GroupObjectivesRel;
import com.netease.okr.model.entity.Objectives;

public interface GroupObjectivesDao {

	public List<GroupObjectives> getGroupObjectivesList(String deptL1Id);

	public Integer addGroupObjectives(GroupObjectives groupObjectives);

	public Integer deleteGroupObjectives(Integer id);

	public Integer getNextCodeNum(String deptL1Id);

	public Integer updateGroupObjectives(GroupObjectives groupObjectives);

	public Integer addGroupObjectivesRel(List<GroupObjectivesRel> list);

	public Integer deleteGroupObjectivesRel(Integer groupObjectivesId);

	public List<Objectives> getObjectivesList(Integer id);

	public void updateGroupObjectivesListRedis();


}
