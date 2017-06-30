package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Appendix implements Serializable {
	
	private static final long serialVersionUID = 8721176900490484659L;

	private Integer id;
	
	/**
	 * 周报ID
	 * */
	private Integer weekReportId;
	
	/**
	 * nosKey
	 * */
	private String nosKey;

	/**
	 * 附件名称
	 * */
	private String name;
	
	/**
	 * 附件地址
	 * */
	private String url;
	
	private Date modifyTime;
	
	private Date createTime;
	
	
	/**
	 * 附件ID
	 * */
	private Integer appendixId;
	
	/**
	 * 关系ID
	 * */
	private Integer relId;

	/**
	 * 附件名称
	 * */
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeekReportId() {
		return weekReportId;
	}

	public void setWeekReportId(Integer weekReportId) {
		this.weekReportId = weekReportId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getNosKey() {
		return nosKey;
	}

	public void setNosKey(String nosKey) {
		this.nosKey = nosKey;
	}

	public Integer getAppendixId() {
		return appendixId;
	}

	public void setAppendixId(Integer appendixId) {
		this.appendixId = appendixId;
	}

	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	
}
