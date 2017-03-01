package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.WeekReportRel;

public interface WeekReportDao {

	public List<WeekReport> getWeekReportListByKeyResultId(Integer keyResultId);

	public Integer addWeekReport(WeekReport weekReport);

	public Integer updateWeekReport(WeekReport weekReport);

	public Integer addWeekReportRel(List<WeekReportRel> weekReportRelList);

	public Integer deleteWeekReportRel(Integer weekReportId);



}
