package com.netease.okr.service;

import java.util.List;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.WeekReport;

public interface WeekReportService {

	public List<WeekReport> getWeekReportListByKeyResultId(Integer keyResultId);

	public JsonResponse addWeekReports(String type, List<WeekReport> weekReport);

	public JsonResponse updateWeekReports(String type, WeekReport weekReports);



}
