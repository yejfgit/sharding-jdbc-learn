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

	
}
