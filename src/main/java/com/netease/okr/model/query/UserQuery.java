package com.netease.okr.model.query;

import com.netease.okr.model.entity.security.User;


public class UserQuery extends User  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8712719274950446353L;
	
	private String deptId;
	private String desp;
	
	

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}


}
