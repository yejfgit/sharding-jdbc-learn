package com.netease.okr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.netease.okr.model.entity.GroupObjectives;
import com.netease.okr.model.entity.GroupObjectivesRel;

public interface GroupObjectivesDao {

	public List<GroupObjectives> getGroupObjectivesList(@Param(value = "deptL1Id") String deptL1Id);

	public Integer addGroupObjectives(GroupObjectives groupObjectives);

	public Integer deleteGroupObjectives(Integer id);

	public Integer getNextCodeNum(String deptL1Id);

	public Integer updateGroupObjectives(GroupObjectives groupObjectives);

	public Integer addGroupObjectivesRel(List<GroupObjectivesRel> list);

	public Integer deleteGroupObjectivesRel(Integer groupObjectivesId);

	public Integer getObjectivesList(Integer id);


}
