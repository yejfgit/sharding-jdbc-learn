package com.netease.okr.service;

import java.util.List;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.OkrNum;

public interface OkrService {

	public List<Objectives> getMyOkrList(Integer userId);

	public JsonResponse addObjectives(Objectives objectives);

	public JsonResponse addKeyResult(KeyResult keyResult);

	public OkrNum getMyOkrNum(Integer userId);

	public Integer updateKeyResultStatus(Integer keyResultId, Integer status);

}
