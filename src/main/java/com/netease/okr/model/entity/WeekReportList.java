package com.netease.okr.model.entity;

import java.util.List;

public class WeekReportList  {
	
	private String type;//接口类型
	
	private List<WeekReport> weekReport;

	public List<WeekReport> getWeekReport() {
		return weekReport;
	}

	public void setWeekReport(List<WeekReport> weekReport) {
		this.weekReport = weekReport;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
