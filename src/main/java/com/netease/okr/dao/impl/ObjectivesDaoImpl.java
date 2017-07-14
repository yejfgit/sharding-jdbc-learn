package com.netease.okr.dao.impl;


import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.ObjectivesDao;
import com.netease.okr.mapper.okr.ObjectivesMapper;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.ObjectivesSummary;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.redis.RedisClient;
import com.netease.okr.redis.RedisConstant;
import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.MyStringUtil;
import com.netease.okr.util.RedisUserContextUtil;

@Repository
public class ObjectivesDaoImpl extends SqlSessionDaoSupport implements ObjectivesDao {
	
	@Autowired
	private ObjectivesMapper objectivesMapper;
	
	/**
	 * @author yejf
	 * @param userId
	 * @return List<Objectives>
	 * @throws DataAccessException
	 */
	@Override
	public List<Objectives> getMyOkrList(Integer userId) {
		return objectivesMapper.getMyOkrList(userId);
	}
	
	@Override
	public List<Objectives> getMyNormalOkrList(Integer userId) {
		return objectivesMapper.getMyNormalOkrList(userId);
	}
	
	
	
	@Override
	public Objectives getObjectivesById(Integer id) {
		return objectivesMapper.getObjectivesById(id);
	}
	
	@Override
	public Objectives getObjectivesInfoById(Integer id) {
		return objectivesMapper.getObjectivesInfoById(id);
	}
	
	@Override
	public Integer getNextCodeNum(Integer userId){
		return objectivesMapper.getNextCodeNum(userId);
	}
	
	@Override
	public Integer getObjectivesCount(Integer userId){
		
		//先从缓存里读取
		String  objectivesCount = RedisClient.get(RedisConstant.OBJECTIVES_COUNT_KEY+userId);
		if(MyStringUtil.isNotBlank(objectivesCount)){
			return Integer.parseInt(objectivesCount);
		}else{
			Integer oc = objectivesMapper.getObjectivesCount(userId);
			RedisClient.set(RedisConstant.OBJECTIVES_COUNT_KEY + userId, oc.toString());
			RedisClient.expire(RedisConstant.OBJECTIVES_COUNT_KEY + userId, RedisConstant.OBJECTIVES_COUNT_EXPIRE);
			return oc;
		}

	}
	
	
	@Override
	public Integer addObjectives(Objectives objectives){
		Integer c =  objectivesMapper.addObjectives(objectives);
		
		updateObjectivesCountOfRedis();
		
		return c;
	}
	
	@Override
	public Integer deleteObjectives(Integer id){
		Integer c =  objectivesMapper.deleteObjectives(id);
		
		updateObjectivesCountOfRedis();
		
		return c;
	}
	
	@Override
	public Integer updateObjectives(Objectives objectives){
		return objectivesMapper.updateObjectives(objectives);
	}
	
	
	@Override
	public ObjectivesSummary updateObjectivesSummary(Integer summaryId,Objectives objectives){
		try {
			if(objectives!=null&&objectives.getId()!=null&&summaryId!=null){
				
				ObjectivesSummary objectivesSummary = new ObjectivesSummary();
				objectivesSummary.setObjectivesId(objectives.getId());
				objectivesSummary.setSummaryId(summaryId);
				objectivesSummary.setComplete(objectives.getComplete());
				objectivesSummary.setCompleteDetail(objectives.getCompleteDetail());
				objectivesMapper.addObjectivesSummary(objectivesSummary);
				return objectivesSummary;
			}else{
				return null;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error(" updateObjectivesSummary exception ", e);
			return null;
		}
		 
	}
	
	
	
	@Override
	public Integer deleteObjectivesSummary(Integer summaryId){
		return objectivesMapper.deleteObjectivesSummary(summaryId);
	}
	
	@Override
	public List<ObjectivesSummary> getObjectivesSummarylistBySummaryId(Integer summaryId){
		return objectivesMapper.getObjectivesSummarylistBySummaryId(summaryId);
	}
	@Override
	public List<Objectives> getSummaryOkrDetail(Integer summaryId) {
		return objectivesMapper.getSummaryOkrDetail(summaryId);
	}
	
	@Override
	public ObjectivesSummary getObjectivesSummary(Integer summaryId,Integer objectivesId) {
		return objectivesMapper.getObjectivesSummary(summaryId,objectivesId);
	}
	
	
	/**
	 * 
	 * 更新redis 周报数量
	 * */
	private Integer updateObjectivesCountOfRedis(){
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		Integer userId = user.getId();
		Integer objectivesCount = objectivesMapper.getObjectivesCount(userId);
		RedisClient.set(RedisConstant.OBJECTIVES_COUNT_KEY + userId, objectivesCount.toString());
		RedisClient.expire(RedisConstant.OBJECTIVES_COUNT_KEY + userId, RedisConstant.OBJECTIVES_COUNT_EXPIRE);
		return objectivesCount;
	}
	
}
