package com.netease.okr.model.entity;
import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class Summary extends BaseEntity implements Serializable {

	private Integer id;
	
	private String name;

	private Integer dateTabId;

	private Integer userId;
	
	/**
	 * 年份
	 * */
	private String year;
	
	/**
	 * 时间分类
	 * */
	private String dateClass;
	
	
	private List<Objectives> objectivesList;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}


	public Integer getDateTabId() {
		return dateTabId;
	}

	public void setDateTabId(Integer dateTabId) {
		this.dateTabId = dateTabId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDateClass() {
		return dateClass;
	}

	public void setDateClass(String dateClass) {
		this.dateClass = dateClass;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Objectives> getObjectivesList() {
		return objectivesList;
	}

	public void setObjectivesList(List<Objectives> objectivesList) {
		this.objectivesList = objectivesList;
	}

}
