package com.netease.okr.service.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.GroupObjectivesDao;
import com.netease.okr.dao.GroupObjectivesMileDao;
import com.netease.okr.dao.GroupObjectivesUserDao;
import com.netease.okr.model.entity.GroupObjectives;
import com.netease.okr.model.entity.GroupObjectivesUser;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.GroupOkrService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.RedisUserContextUtil;

@Repository
public class GroupOkrServiceImpl extends SqlSessionDaoSupport implements GroupOkrService {

	@Autowired
	private GroupObjectivesDao groupObjectivesDao;

	@Autowired
	private GroupObjectivesUserDao groupObjectivesUserDao;
	
	@Autowired
	private GroupObjectivesMileDao groupObjectivesMileDao;

	
	@Override
	public GroupObjectives addGroupObjectives(GroupObjectives groupObjectives) {
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		int c = 0;
		
		Integer nextNum = getNextObjectivesNum();
		//设置目标编号
		groupObjectives.setCodeNum(nextNum);
		//设置目标名称
		groupObjectives.setObjectivesName(ConstantsUtil.OBJECTIVES_NAME_PRE+nextNum);
		
		groupObjectives.setDeptL1Id(user.getDeptL1Id());
		
		c = groupObjectivesDao.addGroupObjectives(groupObjectives);
		
		//添加负责人和参与人
		if(c>0){
			groupObjectivesUserDao.deleteGroupObjectivesUser(groupObjectives.getId());
			List<GroupObjectivesUser> userRels = groupObjectives.getUserList();
			
			for(GroupObjectivesUser goUser:userRels){
				goUser.setGroupObjectivesId(groupObjectives.getId());
				groupObjectivesUserDao.addGroupObjectivesUser(goUser);
			}
			
		}

		return groupObjectives;
	}
	
	
	@Override
	public GroupObjectives updateGroupObjectives(GroupObjectives groupObjectives) {
		int c = 0;
		c = groupObjectivesDao.updateGroupObjectives(groupObjectives);
		//添加负责人和参与人
		if(c>0){
			groupObjectivesUserDao.deleteGroupObjectivesUser(groupObjectives.getId());
			List<GroupObjectivesUser> userRels = groupObjectives.getUserList();
			
			for(GroupObjectivesUser goUser:userRels){
				goUser.setGroupObjectivesId(groupObjectives.getId());
				groupObjectivesUserDao.addGroupObjectivesUser(goUser);
			}
			
		}

		return groupObjectives;
	}
	
	@Override
	public GroupObjectives deleteGroupObjectives(GroupObjectives groupObjectives) {
		int c = 0;
		Integer groupObjectivesId = groupObjectives.getId();
		c = groupObjectivesDao.deleteGroupObjectives(groupObjectivesId);
		//添加负责人和参与人
		if(c>0){
			groupObjectivesUserDao.deleteGroupObjectivesUser(groupObjectivesId);//删除负责人和参与人
			groupObjectivesMileDao.deleteGroupObjectivesMile(groupObjectivesId);//删除组织里程碑
			groupObjectivesDao.deleteGroupObjectivesRel(groupObjectivesId);//删除个人目标关系
			
		}

		return groupObjectives;
	}
	
	
	/**
	 * 
	 * 获取目标当前编号
	 * */
	private Integer getNextObjectivesNum(){
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		Integer codeNum = groupObjectivesDao.getNextCodeNum(user.getDeptL1Id());
		
		return codeNum;
		
	}
}
