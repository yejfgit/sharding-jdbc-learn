package com.netease.okr.model.entity;

import java.io.Serializable;

public class KeyResult  extends BaseEntity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -202732882395468262L;

	private Integer id;
	
	/**
	 * 关键指标名称
	 * */
	private String keyResultName;
	
	/**
	 * 关键指标编号
	 * */
	private String keyResultCode;
	
	/**
	 * 目标Id
	 * */
	private Integer objectivesId;
	
	/**
	 * 状态
	 * */
	private Integer status;
	
	/**
	 * 是否有效
	 * */
	private Integer isValid;
	
	/**
	 * 是否有周报
	 * */
	private Integer isWeek;
	
	/**
	 * 评分
	 * */
	private String score;
	
	/**
	 * code编号
	 * */
	private Integer codeNum;
	
	private Objectives objectives;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getKeyResultName() {
		return keyResultName;
	}


	public void setKeyResultName(String keyResultName) {
		this.keyResultName = keyResultName;
	}


	public String getKeyResultCode() {
		return keyResultCode;
	}


	public void setKeyResultCode(String keyResultCode) {
		this.keyResultCode = keyResultCode;
	}


	public Integer getObjectivesId() {
		return objectivesId;
	}


	public void setObjectivesId(Integer objectivesId) {
		this.objectivesId = objectivesId;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
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
	
	public Objectives getObjectives() {
		return objectives;
	}
	
	public void setObjectives(Objectives objectives) {
		this.objectives = objectives;
	}


	public Integer getIsWeek() {
		return isWeek;
	}


	public void setIsWeek(Integer isWeek) {
		this.isWeek = isWeek;
	}


	public String getScore() {
		return score;
	}


	public void setScore(String score) {
		this.score = score;
	}

}
