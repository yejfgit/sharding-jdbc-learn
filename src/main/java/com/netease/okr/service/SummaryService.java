package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.Summary;

public interface SummaryService {

	public List<Summary> getSummaryList(Integer userId);

	public Boolean addSummary(Summary summary);

	public Boolean updateSummary(Summary summary);

	public Boolean delSummary(Integer summaryId);

	public Summary getSummaryById(Integer id);

	public Integer getSummaryCountOfDate(Integer dateTabId);

}
