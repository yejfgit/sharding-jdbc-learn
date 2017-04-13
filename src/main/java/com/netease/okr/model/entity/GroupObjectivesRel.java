package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;

public class GroupObjectivesRel implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5899898342819938559L;


	private Integer id;
	
	private Integer objectivesId;
	
	private Integer groupObjectivesId;
	
	private Date modifyTime;
	
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public Integer getObjectivesId() {
		return objectivesId;
	}

	public void setObjectivesId(Integer objectivesId) {
		this.objectivesId = objectivesId;
	}

	public Integer getGroupObjectivesId() {
		return groupObjectivesId;
	}

	public void setGroupObjectivesId(Integer groupObjectivesId) {
		this.groupObjectivesId = groupObjectivesId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}
