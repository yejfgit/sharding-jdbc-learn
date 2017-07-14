package com.netease.okr.model.entity;

import java.io.Serializable;

public class ObjectivesSummary extends BaseEntity implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -202732882395468262L;

	private Integer id;
	
	private Integer objectivesId;
	
	private Integer summaryId;
	
	private String complete;
	
	private String completeDetail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getObjectivesId() {
		return objectivesId;
	}

	public void setObjectivesId(Integer objectivesId) {
		this.objectivesId = objectivesId;
	}

	public Integer getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(Integer summaryId) {
		this.summaryId = summaryId;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public String getCompleteDetail() {
		return completeDetail;
	}

	public void setCompleteDetail(String completeDetail) {
		this.completeDetail = completeDetail;
	}
	

}
