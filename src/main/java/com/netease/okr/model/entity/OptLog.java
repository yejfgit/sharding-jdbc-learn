package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;

public class OptLog implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8317428442001110908L;

	private String id;
	
	private Integer userId;
	
	private String url;
	
	private String ip;
	
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
