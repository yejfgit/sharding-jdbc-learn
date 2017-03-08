package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.KeyResult;

public interface KeyResultDao {

	public List<KeyResult> getKeyResultListByoId(Integer objectivesId);

	public Integer getALLKeyResultListCountByoId(Integer objectivesId);

	public Integer addKeyResult(KeyResult keyResult);

	public Integer deleteKeyResult(Integer id);

	public Integer updateKeyResult(KeyResult keyResult);

	public Integer updateKeyResultStatus(List<KeyResult> keyResults);


}
