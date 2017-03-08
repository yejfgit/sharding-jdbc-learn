package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3852854139687966650L;
	
	private String type;//接口类型
	
	private Date modifyTime;
	
	private Date createTime;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	

}
