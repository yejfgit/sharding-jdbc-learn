package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.Objectives;

public interface ObjectivesDao {

	public List<Objectives> getMyOkrList(Integer userId);

	public Integer addObjectives(Objectives objectives);

	public Integer deleteObjectives(Integer id);

	public Integer updateObjectives(Objectives objectives);

	public Integer getAllMyOkrListCount(Integer userId);

	public Objectives getObjectivesById(Integer id);


}
