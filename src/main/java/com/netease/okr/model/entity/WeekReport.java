package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WeekReport  extends BaseEntity implements Serializable {


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
	 * 用户id
	 * */
	private Integer userId;
	
	/**
	 * 时间关系id
	 * */
	private Integer dateId;
	
	
	/**
	 * 周报年份
	 * */
	private String year;
	
	/**
	 * 周报月份
	 * */
	private String month;
	
	
	/**
	 * 周报周数
	 * */
	private String week;
	
	/**
	 * 一周开始日期
	 * */
	private Date startDate;
	
	
	/**
	 * 一周结束日期
	 * */
	private Date endDate;
	
	
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
	public Integer getDateId() {
		return dateId;
	}
	public void setDateId(Integer dateId) {
		this.dateId = dateId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
