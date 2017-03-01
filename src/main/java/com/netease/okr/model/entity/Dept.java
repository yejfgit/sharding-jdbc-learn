package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.List;

public class Dept implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4959491057551578796L;
	private String id;
	
	/**
	 * 部门名称
	 * */
	private String name;
	
	private String nameEn;
	
	/**
	 * 部门等级
	 * */
	private Integer level;
	
	/**
	 * 上级部门ID
	 * */
	private String parentId;
	private Integer displayOrder;
	
	/**
	 * ehr部门Id
	 * */
	private String ehrDeptId;
	
	/**
	 * 部门领导工号
	 * */
	private String deptLeaderUNo;
	
	/**
	 * 部门hr工号
	 * */
	private String deptHrUNo;
	private Integer status;

	
	private List<Dept> children;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getEhrDeptId() {
		return ehrDeptId;
	}

	public void setEhrDeptId(String ehrDeptId) {
		this.ehrDeptId = ehrDeptId;
	}

	public String getDeptLeaderUNo() {
		return deptLeaderUNo;
	}

	public void setDeptLeaderUNo(String deptLeaderUNo) {
		this.deptLeaderUNo = deptLeaderUNo;
	}

	public String getDeptHrUNo() {
		return deptHrUNo;
	}

	public void setDeptHrUNo(String deptHrUNo) {
		this.deptHrUNo = deptHrUNo;
	}

	public List<Dept> getChildren() {
		return children;
	}

	public void setChildren(List<Dept> children) {
		this.children = children;
	}


}
