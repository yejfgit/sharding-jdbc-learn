package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.DateInfo;

public interface DateInfoService {

	public Integer addDateInfo(DateInfo dateInfo);

	public void createDateInfo(String startDateStr,String endDateStr);

	public List<DateInfo> getAllDateInfo();

	public DateInfo getCurDateInfo();

}
