package com.netease.okr.service;

import java.util.List;

import com.netease.ehrSDK.Department;
import com.netease.ehrSDK.Employee;

public interface EhrDateService {

	public void syncDept();

	public void syncUser();

	public List<Department> getDeptL1List();

	public List<Employee> queryUser(String deptL1Id);

}
