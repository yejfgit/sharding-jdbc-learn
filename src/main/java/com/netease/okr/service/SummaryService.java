package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.Summary;

public interface SummaryService {

	public List<Summary> getSummaryList(Integer userId);


}
