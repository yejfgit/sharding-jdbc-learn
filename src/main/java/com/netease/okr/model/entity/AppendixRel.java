package com.netease.okr.model.entity;

import java.io.Serializable;
import java.util.Date;

public class AppendixRel extends  Appendix implements Serializable {
	
	private static final long serialVersionUID = 8721176900490484659L;

	private Integer id;
	
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
	
	private Date modifyTime;
	
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
