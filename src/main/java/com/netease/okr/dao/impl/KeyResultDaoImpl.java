package com.netease.okr.dao.impl;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.KeyResultDao;
import com.netease.okr.mapper.okr.KeyResultMapper;
import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.KeyResultScore;

@Repository
public class KeyResultDaoImpl extends SqlSessionDaoSupport implements KeyResultDao {
	
	@Autowired
	private KeyResultMapper keyResultMapper;
	
	
	/**
	 * @author yejf
	 * @param userId
	 * @return List<Objectives>
	 * @throws DataAccessException
	 */
	@Override
	public List<KeyResult> getKeyResultListByoId(Integer objectivesId) {
		return keyResultMapper.getKeyResultListByoId(objectivesId);
	}
	
	
	@Override
	public Integer getNextCodeNum(Integer objectivesId){
		return keyResultMapper.getNextCodeNum(objectivesId);
	}
	
	@Override
	public Integer getKeyResultNumOfStart(Integer objectivesId){
		return keyResultMapper.getKeyResultNumOfStart(objectivesId);
	}
	
	
	@Override
	public Integer addKeyResult(KeyResult keyResult){
		return keyResultMapper.addKeyResult(keyResult);
	}
	
	@Override
	public Integer deleteKeyResult(Integer id){
		return keyResultMapper.deleteKeyResult(id);
	}
	
	@Override
	public Integer deleteKeyResultByoId(Integer objectivesId){
		return keyResultMapper.deleteKeyResultByoId(objectivesId);
	}
	
	@Override
	public Integer updateKeyResult(KeyResult keyResult){
		return keyResultMapper.updateKeyResult(keyResult);
	}
	
	@Override
	public Integer updateKeyResultStatus(List<KeyResult> keyResults){
		if(keyResults==null||keyResults.size()<1){
			return 0;
		}
		return keyResultMapper.updateKeyResultStatus(keyResults);
	}
	
	
	@Override
	public Integer updateKeyResultScore(Integer summaryId,KeyResult keyResult){
		
		if(keyResult!=null&&keyResult.getId()!=null&&summaryId!=null){
			
			KeyResultScore keyResultScore = new KeyResultScore();
			keyResultScore.setKeyResultId(keyResult.getId());
			keyResultScore.setSummaryId(summaryId);
			keyResultScore.setScore(keyResult.getScore());
			keyResultMapper.addKeyResultScore(keyResultScore);
		}
		return 1;
		
	}
	
	@Override
	public Integer deleteKeyResultScore(Integer summaryId){
		return keyResultMapper.deleteKeyResultScore(summaryId);
		
	}
	
	@Override
	public List<KeyResultScore> getKeyResultScorelistBySummaryId(@Param(value = "summaryId") Integer summaryId){
		return keyResultMapper.getKeyResultScorelistBySummaryId(summaryId);
		
	}
	@Override
	public KeyResultScore getKeyResultScore(Integer summaryId,Integer keyResultId){
		return keyResultMapper.getKeyResultScore(summaryId,keyResultId);
		
	}
	
}
