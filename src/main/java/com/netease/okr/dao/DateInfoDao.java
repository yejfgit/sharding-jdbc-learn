package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.DateInfo;

public interface DateInfoDao {

	public Integer addDateInfo(DateInfo dateInfo);

	public List<DateInfo> getAllDateInfo();

	public DateInfo getCurDateInfo();


}
