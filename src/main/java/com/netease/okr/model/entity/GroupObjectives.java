package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.List;

public class GroupObjectives extends BaseEntity implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -202732882395468262L;

	private Integer id;
	
	/**
	 * 目标名称
	 * */
	private String objectivesName;
	
	/**
	 * 目标描述
	 * */
	private String objectivesDsc;
	
	/**
	 * 部门ID
	 * */
	private String deptL1Id;
	
	
	/**
	 * 是否有效
	 * */
	private Integer isValid;
	
	/**
	 * code编号
	 * */
	private Integer codeNum;
	
	
	private List<GroupObjectivesUser> userList;
	
	private List<GroupObjectivesMile> mileList;
	
	private List<Objectives> objectivesList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObjectivesName() {
		return objectivesName;
	}

	public void setObjectivesName(String objectivesName) {
		this.objectivesName = objectivesName;
	}

	public String getObjectivesDsc() {
		return objectivesDsc;
	}

	public void setObjectivesDsc(String objectivesDsc) {
		this.objectivesDsc = objectivesDsc;
	}

	public String getDeptL1Id() {
		return deptL1Id;
	}

	public void setDeptL1Id(String deptL1Id) {
		this.deptL1Id = deptL1Id;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(Integer codeNum) {
		this.codeNum = codeNum;
	}

	public List<GroupObjectivesUser> getUserList() {
		return userList;
	}

	public void setUserList(List<GroupObjectivesUser> userList) {
		this.userList = userList;
	}

	public List<GroupObjectivesMile> getMileList() {
		return mileList;
	}

	public void setMileList(List<GroupObjectivesMile> mileList) {
		this.mileList = mileList;
	}

	public List<Objectives> getObjectivesList() {
		return objectivesList;
	}

	public void setObjectivesList(List<Objectives> objectivesList) {
		this.objectivesList = objectivesList;
	}
	
	
}
