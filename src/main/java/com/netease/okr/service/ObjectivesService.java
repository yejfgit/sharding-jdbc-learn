package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.Objectives;

public interface ObjectivesService {

	public Objectives getObjectivesById(Integer id);

	public List<Objectives> getSummaryOkrDetail(Integer summaryId);

	public List<Objectives> getEditSummaryOkrDetail(Integer summaryId);


}
