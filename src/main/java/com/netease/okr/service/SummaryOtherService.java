package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.SummaryOther;

public interface SummaryOtherService {

	public List<SummaryOther> getSummaryOtherList(Integer summaryId);

	public Boolean addSummaryOtherList(List<SummaryOther> summaryOtherList);


}
