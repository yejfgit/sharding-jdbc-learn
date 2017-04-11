package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.Dept;

public interface DeptService {

	public Dept getDeptById(String id);

	public Integer addDept(Dept dept);

	public Integer deleteDept();

	public List<Dept> getAllDept();

	public List<Dept> getDeptListByLevel(Integer level);

}
