package com.netease.okr.service;

import java.util.List;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.OkrNum;

public interface OkrService {

	public List<Objectives> getMyOkrList(Integer userId);

	public JsonResponse addObjectives(String type, Objectives objectives);

	public JsonResponse addKeyResult(String type, KeyResult keyResult);

	public OkrNum getMyOkrNum(Integer userId);

}
