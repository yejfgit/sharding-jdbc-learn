package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;

public class DateTabReport implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6577891037945190958L;

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
	 * 周报周数
	 * */
	private String week;
	private Date modify_time;
	private Date create_time;
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
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	

}
