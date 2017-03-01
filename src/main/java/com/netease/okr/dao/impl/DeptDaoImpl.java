package com.netease.okr.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.DeptDao;
import com.netease.okr.mapper.okr.DeptMapper;
import com.netease.okr.model.entity.Dept;

@Repository
public class DeptDaoImpl extends SqlSessionDaoSupport implements DeptDao {
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public Dept getDeptById(String id){
		return deptMapper.getDeptById(id);
	}
	
	@Override
	public Integer addDept(Dept dept){
		return deptMapper.addDept(dept);
	}
	
	@Override
	public Integer deleteDept(){
		return deptMapper.deleteDept();
	}
	
	@Override
	public List<Dept> getDeptListByLevel(Integer level){
		return deptMapper.getDeptListByLevel(level);
	}
	
	@Override
	public List<Dept> getDeptListByParentId(String parentId){
		return deptMapper.getDeptListByParentId(parentId);
	}
	
}
