package com.netease.okr.dao.impl;


import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.KeyResultDao;
import com.netease.okr.mapper.okr.KeyResultMapper;
import com.netease.okr.model.entity.KeyResult;

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
	public Integer getALLKeyResultListCountByoId(Integer objectivesId){
		return keyResultMapper.getALLKeyResultListCountByoId(objectivesId);
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
	public Integer updateKeyResult(KeyResult keyResult){
		return keyResultMapper.updateKeyResult(keyResult);
	}
	
	@Override
	public Integer updateKeyResultStatus(List<KeyResult> keyResults){
		return keyResultMapper.updateKeyResultStatus(keyResults);
	}
}
