package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.Objectives;

public interface ObjectivesDao {

	public List<Objectives> getMyOkrList(Integer userId);

	public Integer addObjectives(Objectives objectives);

	public Integer deleteObjectives(Integer id);

	public Integer updateObjectives(Objectives objectives);

	public Objectives getObjectivesById(Integer id);

	public Integer getNextCodeNum(Integer userId);

	public Integer getObjectivesCount(Integer userId);

	public Objectives getObjectivesInfoById(Integer id);

	public List<Objectives> getMyNormalOkrList(Integer userId);

	public Integer deleteAllObjectivesSummaryOfUser(Integer userId);


}
