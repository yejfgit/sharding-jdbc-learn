package com.netease.okr.service.impl;


import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.KeyResultDao;
import com.netease.okr.dao.ObjectivesDao;
import com.netease.okr.mapper.okr.AppendixMapper;
import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.KeyResultScore;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.ObjectivesSummary;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.ObjectivesService;
import com.netease.okr.util.RedisUserContextUtil;

@Repository
public class ObjectivesServiceImpl extends SqlSessionDaoSupport implements ObjectivesService {
	
	@Autowired
	private ObjectivesDao objectivesDao;
	
	@Autowired
	private KeyResultDao keyResultDao;
	
	@Autowired
	private AppendixMapper appendixMapper;

	@Override
	public Objectives getObjectivesById(Integer id) {
		return objectivesDao.getObjectivesById(id);
	}
	
	@Override
	public List<Objectives> getSummaryOkrDetail(Integer summaryId) {
		return objectivesDao.getSummaryOkrDetail(summaryId);
	}
	
	@Override
	public List<Objectives> getEditSummaryOkrDetail(Integer summaryId) {
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		List<Objectives> objectivesList = objectivesDao.getMyOkrList(user.getId());
		
		//组装目标总结信息
		setObjectivesSummaryDetail(summaryId,objectivesList);
		
		return objectivesList;
	}
	
	
	private void setObjectivesSummaryDetail(Integer summaryId,List<Objectives> objectivesList){
		
		if(objectivesList!=null&&objectivesList.size()>0){
			for(Objectives objectives:objectivesList){
				ObjectivesSummary objectivesSummary = objectivesDao.getObjectivesSummary(summaryId, objectives.getId());
				if(objectivesSummary!=null){
					objectives.setComplete(objectivesSummary.getComplete());
					objectives.setCompleteDetail(objectivesSummary.getCompleteDetail());
					
					objectives.setAppendixList(appendixMapper.getAppendixRelListOfObjectivesSummary(objectivesSummary.getId()));
				}
				
				//组装关键事件打分详情
				setKeyResultScoreDetail(summaryId,objectives.getKeyResultList());
				
			}
		}
	}
	
	
	private void setKeyResultScoreDetail(Integer summaryId,List<KeyResult> keyResultList){
		if(keyResultList!=null&&keyResultList.size()>0){
			for(KeyResult keyResult:keyResultList){
				KeyResultScore keyResultScore = keyResultDao.getObjectivesSummary(summaryId, keyResult.getId());
				if(keyResultScore!=null){
					keyResult.setScore(keyResultScore.getScore());
				}
			}
		}
	}
}
