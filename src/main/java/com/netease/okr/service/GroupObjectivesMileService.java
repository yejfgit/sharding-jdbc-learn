package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.GroupObjectivesMile;

public interface GroupObjectivesMileService {

	public List<GroupObjectivesMile> getGroupObjectivesMiles(Integer groupObjectivesId);

	public Integer addGroupObjectivesMile(GroupObjectivesMile groupObjectivesMile);

	public Integer deleteGroupObjectivesMile(Integer id);

	public Integer updateGroupObjectivesMile(GroupObjectivesMile groupObjectivesMile);

	public Integer deleteGroupObjectivesMileById(Integer id);


}
