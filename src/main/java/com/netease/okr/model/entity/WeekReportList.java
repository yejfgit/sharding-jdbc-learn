package com.netease.okr.model.entity;

import java.util.List;

public class WeekReportList  {
	
	private String type;//接口类型
	
	private List<WeekReport> weekList;



	public List<WeekReport> getWeekList() {
		return weekList;
	}

	public void setWeekList(List<WeekReport> weekList) {
		this.weekList = weekList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
