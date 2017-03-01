package com.netease.okr.model.entity;

import java.io.Serializable;

public class WeekReportRel implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5899898342819938559L;


	private Integer id;
	
	private Integer weekReportId;
	
	private Integer keyResultId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeekReportId() {
		return weekReportId;
	}

	public void setWeekReportId(Integer weekReportId) {
		this.weekReportId = weekReportId;
	}

	public Integer getKeyResultId() {
		return keyResultId;
	}

	public void setKeyResultId(Integer keyResultId) {
		this.keyResultId = keyResultId;
	}
	
	
}
