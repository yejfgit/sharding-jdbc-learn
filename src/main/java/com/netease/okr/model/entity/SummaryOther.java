package com.netease.okr.model.entity;
import java.io.Serializable;
import java.util.List;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class SummaryOther implements Serializable {

	/****/
	private Integer id;

	/****/
	private String name;

	/****/
	private String content;

	/**总结时间**/
	private Integer dateTabId;
	
	private Integer summaryId;
	/****/
	private Integer userId;

	/****/
	private java.util.Date modifyTime;

	/****/
	private java.util.Date createTime;

	/**
	 * 附件list
	 * */
	private List<Appendix> appendixList;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setDateTabId(Integer dateTabId){
		this.dateTabId = dateTabId;
	}

	public Integer getDateTabId(){
		return this.dateTabId;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setModifyTime(java.util.Date modifyTime){
		this.modifyTime = modifyTime;
	}

	public java.util.Date getModifyTime(){
		return this.modifyTime;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public List<Appendix> getAppendixList() {
		return appendixList;
	}

	public void setAppendixList(List<Appendix> appendixList) {
		this.appendixList = appendixList;
	}

	public Integer getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(Integer summaryId) {
		this.summaryId = summaryId;
	}

}
