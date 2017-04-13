package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.netease.okr.model.entity.security.User;

public class GroupObjectivesUser extends User implements Serializable  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5609184169766047316L;

	private Integer id;
	
	private Integer userId;
	
	private Integer groupObjectivesId;

	private Integer userType;

	private Date modifyTime;
	
	private Date createTime;

	private List<Objectives> objectivesList;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getUserType() {
		return userType;
	}


	public void setUserType(Integer userType) {
		this.userType = userType;
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


	public Integer getGroupObjectivesId() {
		return groupObjectivesId;
	}


	public void setGroupObjectivesId(Integer groupObjectivesId) {
		this.groupObjectivesId = groupObjectivesId;
	}


	public List<Objectives> getObjectivesList() {
		return objectivesList;
	}


	public void setObjectivesList(List<Objectives> objectivesList) {
		this.objectivesList = objectivesList;
	}


	
	
}
