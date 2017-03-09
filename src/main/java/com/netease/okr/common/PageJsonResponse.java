package com.netease.okr.common;

import java.util.List;

public class PageJsonResponse<T> {
	
	private PageBean<T> page;
	
	private List<T> objList;

	public PageBean<T> getPage() {
		return page;
	}

	public void setPage(PageBean<T> page) {
		this.page = page;
	}

	public List<T> getObjList() {
		return objList;
	}

	public void setObjList(List<T> objList) {
		this.objList = objList;
	}
	
	
	
	
}
