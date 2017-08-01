package com.netease.learn.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7886450074801913332L;
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private Date createTime;
	
	@Getter
	@Setter
	private Date modifyTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
}
