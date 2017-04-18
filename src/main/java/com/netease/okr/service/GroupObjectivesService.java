package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.GroupObjectives;
import com.netease.okr.model.entity.GroupObjectivesRelList;
import com.netease.okr.model.entity.Objectives;

public interface GroupObjectivesService {

	public List<GroupObjectives> getGroupObjectivesList(String deptL1Id);

	public Integer addGroupObjectives(GroupObjectives groupObjectives);

	public Integer deleteGroupObjectives(Integer id);

	public Integer getNextCodeNum(String deptL1Id);

	public Integer updateGroupObjectives(GroupObjectives groupObjectives);

	public Integer addGroupObjectivesRel(GroupObjectivesRelList groupObjectivesRelList);

	public Integer deleteGroupObjectivesRel(Integer groupObjectivesId);

	public List<Objectives> getObjectivesList(Integer id);


}
