package com.netease.okr.common;

import java.util.List;

public class UserContext implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7111406773106196480L;

	private Object user;
	
	private List<?> rightList;

	public List<?> getRightList() {
		return rightList;
	}

	public void setRightList(List<?> rightList) {
		this.rightList = rightList;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object obj) {
		this.user = obj;
	}
	
	
}
