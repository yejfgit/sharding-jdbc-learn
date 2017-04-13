package com.netease.okr.dao.impl;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.GroupObjectivesUserDao;
import com.netease.okr.mapper.okr.GroupObjectivesUserMapper;
import com.netease.okr.model.entity.GroupObjectivesUser;

@Repository
public class GroupObjectivesUserDaoImpl extends SqlSessionDaoSupport implements GroupObjectivesUserDao {
	
	@Autowired
	private GroupObjectivesUserMapper groupObjectivesUserMapper;

	@Override
	public List<GroupObjectivesUser> getGroupObjectivesUsers(@Param(value = "groupObjectivesId") Integer groupObjectivesId){
		return groupObjectivesUserMapper.getGroupObjectivesUsers(groupObjectivesId);
	}

	@Override
	public Integer addGroupObjectivesUser(GroupObjectivesUser groupObjectivesUser)  {
		return groupObjectivesUserMapper.addGroupObjectivesUser(groupObjectivesUser);
	}
	
	@Override
	public Integer deleteGroupObjectivesUser(Integer id){
		return groupObjectivesUserMapper.deleteGroupObjectivesUser(id);
	}
	
	
}
