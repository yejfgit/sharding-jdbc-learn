package com.netease.okr.model.entity;

import java.io.Serializable;

public class OkrNum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2304973233507842437L;

	private Integer userId;
	
	private Integer okrNum;
	
	private Integer weekReportNum;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOkrNum() {
		return okrNum;
	}

	public void setOkrNum(Integer okrNum) {
		this.okrNum = okrNum;
	}

	public Integer getWeekReportNum() {
		return weekReportNum;
	}

	public void setWeekReportNum(Integer weekReportNum) {
		this.weekReportNum = weekReportNum;
	}
	
	
	

}
