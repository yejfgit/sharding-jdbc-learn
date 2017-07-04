package com.netease.okr.dao;

import java.util.List;

import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.WeekReportRel;
import com.netease.okr.model.query.WeekReportQuery;

public interface WeekReportDao {

	public List<WeekReport> getWeekReportListByKeyResultId(Integer keyResultId);

	public Integer addWeekReport(WeekReport weekReport);

	public Integer updateWeekReport(WeekReport weekReport);

	public Integer addWeekReportRel(List<WeekReportRel> weekReportRelList);

	public Integer deleteWeekReportRel(Integer weekReportId);

	public Integer getWeekReportCount(Integer userId);

	public List<WeekReport> getWeekReportList(WeekReportQuery weekReportQuery);

	public Integer deleteWeekReport(Integer id);

	public List<WeekReportRel> getWeekReportRelList(WeekReportRel weekReportRel);

	public WeekReport getNewWeekReport(Integer userId);

	public WeekReport getWeekReportById(Integer id);

	public Integer hasNewWeekReport(Integer userId);

	public void updateRedis();



}
