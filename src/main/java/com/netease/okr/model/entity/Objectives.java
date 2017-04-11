package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.List;

public class Objectives extends BaseEntity implements Serializable  {

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
	
	private String objectivesDetail;
	
	/**
	 * 用户Id
	 * */
	private Integer userId;
	
	
	/**
	 * 是否有效
	 * */
	private Integer isValid;
	
	/**
	 * code编号
	 * */
	private Integer codeNum;
	
	
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
	public List<KeyResult> getKeyResultList() {
		return keyResultList;
	}
	public void setKeyResultList(List<KeyResult> keyResultList) {
		this.keyResultList = keyResultList;
	}
	public Integer getCodeNum() {
		return codeNum;
	}
	public void setCodeNum(Integer codeNum) {
		this.codeNum = codeNum;
	}
	public String getObjectivesDetail() {
		return objectivesDetail;
	}
	public void setObjectivesDetail(String objectivesDetail) {
		this.objectivesDetail = objectivesDetail;
	}
	
	

}
