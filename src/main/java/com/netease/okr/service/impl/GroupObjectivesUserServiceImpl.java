package com.netease.okr.service.impl;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.GroupObjectivesUserDao;
import com.netease.okr.model.entity.GroupObjectivesUser;
import com.netease.okr.service.GroupObjectivesUserService;

@Repository
public class GroupObjectivesUserServiceImpl extends SqlSessionDaoSupport implements GroupObjectivesUserService {
	
	@Autowired
	private GroupObjectivesUserDao groupObjectivesUserDao;

	@Override
	public List<GroupObjectivesUser> getGroupObjectivesUsers(@Param(value = "groupObjectivesId") Integer groupObjectivesId){
		return groupObjectivesUserDao.getGroupObjectivesUsers(groupObjectivesId);
	}

	@Override
	public Integer addGroupObjectivesUser(GroupObjectivesUser groupObjectivesUser)  {
		return groupObjectivesUserDao.addGroupObjectivesUser(groupObjectivesUser);
	}
	
	@Override
	public Integer deleteGroupObjectivesUser(Integer id){
		return groupObjectivesUserDao.deleteGroupObjectivesUser(id);
	}
	
	
}
