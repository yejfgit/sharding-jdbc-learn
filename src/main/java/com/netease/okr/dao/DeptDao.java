package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.Dept;

public interface DeptDao {

	public Dept getDeptById(String id);

	public Integer addDept(Dept dept);

	public Integer deleteDept();

	public List<Dept> getDeptListByLevel(Integer level);

	public List<Dept> getDeptListByParentId(String parentId);


}
