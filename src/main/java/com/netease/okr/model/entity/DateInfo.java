package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;

public class DateInfo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6489855368459577761L;

	private Integer id;
	
	/**
	 * 年份
	 * */
	private String year;
	
	/**
	 * 日期类型
	 * */
	private String dateClass;
	

	private Integer display;
	
	
	private Integer type;
	
	/**
	 * 月份
	 * */
	private String month;
	
	/**
	 * 第几周 W1
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
	
	private Integer isShowCurDate;//1:是 0:否
	


	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIsShowCurDate() {
		return isShowCurDate;
	}
	public void setIsShowCurDate(Integer isShowCurDate) {
		this.isShowCurDate = isShowCurDate;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDateClass() {
		return dateClass;
	}
	public void setDateClass(String dateClass) {
		this.dateClass = dateClass;
	}
	public Integer getDisplay() {
		return display;
	}
	public void setDisplay(Integer display) {
		this.display = display;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	

	
}
