package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Objectives implements Serializable {


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
	 * 目标编号
	 * */
	private String objectivesCode;
	
	/**
	 * 用户Id
	 * */
	private Integer userId;
	
	
	/**
	 * 是否有效
	 * */
	private Integer isValid;
	

	private Date modifyTime;
	private Date createTime;
	
	
	private List<KeyResult> keyResultList;
	
	
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
	public String getObjectivesCode() {
		return objectivesCode;
	}
	public void setObjectivesCode(String objectivesCode) {
		this.objectivesCode = objectivesCode;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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
	public List<KeyResult> getKeyResultList() {
		return keyResultList;
	}
	public void setKeyResultList(List<KeyResult> keyResultList) {
		this.keyResultList = keyResultList;
	}
	
	

}
