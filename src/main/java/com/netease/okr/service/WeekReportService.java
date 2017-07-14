package com.netease.okr.service;

import java.util.List;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.WeekReportList;
import com.netease.okr.model.query.WeekReportQuery;

public interface WeekReportService {

	public List<WeekReport> getWeekReportListByKeyResultId(Integer keyResultId);

	public JsonResponse addWeekReports( WeekReportList weekReportList);

	public JsonResponse updateWeekReports(WeekReport weekReports);

	public List<WeekReport> getWeekReportList(WeekReportQuery weekReportQuery);

	public WeekReport getWeekReportById(Integer id);

	public void updateRedis();



}
