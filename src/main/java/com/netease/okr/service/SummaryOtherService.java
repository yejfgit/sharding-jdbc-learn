package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.SummaryOther;

public interface SummaryOtherService {

	public List<SummaryOther> getSummaryOtherList(Integer summaryId);

	public Boolean addSummaryOtherList(Integer summaryId,List<SummaryOther> summaryOtherList);

	public Boolean updateSummaryOtherList(Integer summaryId, List<SummaryOther> summaryOtherList);

	public Boolean delSummaryOtherList(Integer summaryId);

	public Boolean delSummaryOtherById(Integer id);


}
