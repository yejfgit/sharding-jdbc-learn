package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;

public class GroupObjectivesMile extends BaseEntity implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -202732882395468262L;

	private Integer id;
	
	/**
	 * 里程碑内容
	 * */
	private String mileDsc;
	
	private Integer groupObjectivesId;
	
	/**
	 * 里程碑时间
	 * */
	private Date mileDate;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMileDsc() {
		return mileDsc;
	}


	public void setMileDsc(String mileDsc) {
		this.mileDsc = mileDsc;
	}


	public Date getMileDate() {
		return mileDate;
	}


	public void setMileDate(Date mileDate) {
		this.mileDate = mileDate;
	}


	public Integer getGroupObjectivesId() {
		return groupObjectivesId;
	}


	public void setGroupObjectivesId(Integer groupObjectivesId) {
		this.groupObjectivesId = groupObjectivesId;
	}
	

	
}
