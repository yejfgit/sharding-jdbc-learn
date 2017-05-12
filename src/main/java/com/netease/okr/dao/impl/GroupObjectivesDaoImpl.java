package com.netease.okr.dao.impl;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.netease.okr.dao.GroupObjectivesDao;
import com.netease.okr.mapper.okr.GroupObjectivesMapper;
import com.netease.okr.model.entity.GroupObjectives;
import com.netease.okr.model.entity.GroupObjectivesRel;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.redis.RedisClient;
import com.netease.okr.redis.RedisConstant;
import com.netease.okr.util.MyStringUtil;
import com.netease.okr.util.RedisUserContextUtil;

@Repository
public class GroupObjectivesDaoImpl extends SqlSessionDaoSupport implements GroupObjectivesDao {
	
	@Autowired
	private GroupObjectivesMapper groupObjectivesMapper;
	
	@Override
	public void updateGroupObjectivesListRedis(){
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		String deptL1Id = user.getDeptL1Id();
		String redisKey = RedisConstant.GROUP_OBJECTIVES_INFO_QUERY_KEY+"_"+deptL1Id;
		List<GroupObjectives> gos = groupObjectivesMapper.getGroupObjectivesList(deptL1Id);
		RedisClient.set(redisKey, JSON.toJSONString(gos));
		RedisClient.expire(redisKey, RedisConstant.GROUP_OBJECTIVES_INFO_QUERY_EXPIRE);
	}
	
	@Override
	public List<GroupObjectives> getGroupObjectivesList(String deptL1Id) {

		String redisKey = RedisConstant.GROUP_OBJECTIVES_INFO_QUERY_KEY+"_"+deptL1Id;
		//先从缓存里读取
		String groupObjectivesListStr = RedisClient.get(redisKey);
		if(MyStringUtil.isNotBlank(groupObjectivesListStr)){
			return JSON.parseArray(groupObjectivesListStr, GroupObjectives.class);
		}else{
			List<GroupObjectives> gos = groupObjectivesMapper.getGroupObjectivesList(deptL1Id);
			RedisClient.set(redisKey, JSON.toJSONString(gos));
			RedisClient.expire(redisKey, RedisConstant.GROUP_OBJECTIVES_INFO_QUERY_EXPIRE);
			return gos;
		}
		
	}

	@Override
	public Integer addGroupObjectives(GroupObjectives groupObjectives) {
		return groupObjectivesMapper.addGroupObjectives(groupObjectives);
	}
	@Override
	public List<Objectives> getObjectivesList(Integer id) {
		return groupObjectivesMapper.getObjectivesList(id);
	}
	
	@Override
	public Integer deleteGroupObjectives(Integer id)  {
		return groupObjectivesMapper.deleteGroupObjectives(id);
	}
	
	@Override
	public Integer getNextCodeNum(@Param(value = "deptL1Id") String deptL1Id)  {
		return groupObjectivesMapper.getNextCodeNum(deptL1Id);
	}
	
	@Override
	public Integer updateGroupObjectives(GroupObjectives groupObjectives)  {
		return groupObjectivesMapper.updateGroupObjectives(groupObjectives);
	}
	
	@Override
	public Integer addGroupObjectivesRel(@Param(value = "list") List<GroupObjectivesRel> list) {
		return groupObjectivesMapper.addGroupObjectivesRel(list);
	}
	
	@Override
	public Integer deleteGroupObjectivesRel(@Param(value = "groupObjectivesId") Integer groupObjectivesId) {
		return groupObjectivesMapper.deleteGroupObjectivesRel(groupObjectivesId);
	}
	
}
