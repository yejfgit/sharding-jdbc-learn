package com.netease.okr.model.entity;

import java.io.Serializable;

public class KeyResultScore  extends BaseEntity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -202732882395468262L;

	private Integer id;
	

	private Integer keyResultId;
	
	private Integer summaryId;
	
	private String score;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKeyResultId() {
		return keyResultId;
	}

	public void setKeyResultId(Integer keyResultId) {
		this.keyResultId = keyResultId;
	}

	public Integer getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(Integer summaryId) {
		this.summaryId = summaryId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}
