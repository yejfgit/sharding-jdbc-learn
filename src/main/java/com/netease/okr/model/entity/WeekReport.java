package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WeekReport implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -202732882395468262L;

	private Integer id;
	
	/**
	 * 周报内容
	 * */
	private String content;
	
	/**
	 * 状态0：暂存 1：发布
	 * */
	private Integer status;
	
	/**
	 * 周报年份
	 * */
	private String year;
	
	/**
	 * 周报月份
	 * */
	private String month;
	
	/**
	 * 用户id
	 * */
	private Integer userId;
	
	/**
	 * 周报周数
	 * */
	private String week;
	private Date modifyTime;
	private Date createTime;
	
	/**
	 *关键事件及结果 
	 * */
	private List<KeyResult> keyResultList;
	
	/**
	 * 附件list
	 * */
	private List<Appendix> appendixList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
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
	public List<Appendix> getAppendixList() {
		return appendixList;
	}
	public void setAppendixList(List<Appendix> appendixList) {
		this.appendixList = appendixList;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

}
