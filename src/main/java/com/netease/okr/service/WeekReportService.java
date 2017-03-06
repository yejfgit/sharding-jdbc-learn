package com.netease.okr.service;

import java.util.List;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.query.WeekReportQuery;

public interface WeekReportService {

	public List<WeekReport> getWeekReportListByKeyResultId(Integer keyResultId);

	public JsonResponse addWeekReports(String type, List<WeekReport> weekReport);

	public JsonResponse updateWeekReports(String type, WeekReport weekReports);

	public List<WeekReport> getWeekReportList(WeekReportQuery weekReportQuery);



}
