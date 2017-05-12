package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.GroupObjectivesMile;

public interface GroupObjectivesMileDao {

	public List<GroupObjectivesMile> getGroupObjectivesMiles(Integer groupObjectivesId);

	public Integer addGroupObjectivesMile(GroupObjectivesMile groupObjectivesMile);

	public Integer deleteGroupObjectivesMile(Integer id);

	public Integer updateGroupObjectivesMile(GroupObjectivesMile groupObjectivesMile);

	public Integer deleteGroupObjectivesMileById(Integer id);


}
