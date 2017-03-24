package com.netease.okr.common;

import java.util.List;

import com.netease.okr.model.entity.security.User;

public class UserContext implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7111406773106196480L;

	private User user;
	
	private List<?> rightList;

	public List<?> getRightList() {
		return rightList;
	}

	public void setRightList(List<?> rightList) {
		this.rightList = rightList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
