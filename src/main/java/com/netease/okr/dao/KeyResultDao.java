package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.KeyResultScore;

public interface KeyResultDao {

	public List<KeyResult> getKeyResultListByoId(Integer objectivesId);

	public Integer addKeyResult(KeyResult keyResult);

	public Integer deleteKeyResult(Integer id);

	public Integer updateKeyResult(KeyResult keyResult);

	public Integer updateKeyResultStatus(List<KeyResult> keyResults);

	public Integer getNextCodeNum(Integer objectivesId);

	public Integer getKeyResultNumOfStart(Integer objectivesId);

	public Integer deleteKeyResultByoId(Integer objectivesId);

	public Integer updateKeyResultScore(Integer summaryId, KeyResult keyResult);

	public List<KeyResultScore> getKeyResultScorelistBySummaryId(Integer summaryId);

	public Integer deleteKeyResultScore(Integer summaryId);

	public KeyResultScore getKeyResultScore(Integer summaryId, Integer keyResultId);


}
