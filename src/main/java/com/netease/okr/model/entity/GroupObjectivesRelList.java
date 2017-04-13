package com.netease.okr.model.entity;

import java.util.List;

public class GroupObjectivesRelList  {

	private Integer groupObjectivesId;
	
	private List<Integer> objectivesIdList;

	public Integer getGroupObjectivesId() {
		return groupObjectivesId;
	}

	public void setGroupObjectivesId(Integer groupObjectivesId) {
		this.groupObjectivesId = groupObjectivesId;
	}

	public List<Integer> getObjectivesIdList() {
		return objectivesIdList;
	}

	public void setObjectivesIdList(List<Integer> objectivesIdList) {
		this.objectivesIdList = objectivesIdList;
	}
	
	
}
