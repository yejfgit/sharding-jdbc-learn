package com.netease.okr.dao.impl;


import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.WeekReportDao;
import com.netease.okr.mapper.okr.WeekReportMapper;
import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.WeekReportRel;
import com.netease.okr.model.query.WeekReportQuery;

@Repository
public class WeekReportDaoImpl extends SqlSessionDaoSupport implements WeekReportDao {
	
	@Autowired
	private WeekReportMapper weekReportMapper;
	
	
	/**
	 * @author yejf
	 * @param keyResultId
	 * @return List<WeekReport>
	 * @throws DataAccessException
	 */
	@Override
	public List<WeekReport> getWeekReportListByKeyResultId(Integer keyResultId) {
		return weekReportMapper.getWeekReportListByKeyResultId(keyResultId);
	}
	
	@Override
	public Integer addWeekReport(WeekReport weekReport){
		return weekReportMapper.addWeekReport(weekReport);
	}
	
	
	@Override
	public Integer getWeekReportCount(Integer userId){
		return weekReportMapper.getWeekReportCount(userId);
	}
	
	
	@Override
	public WeekReport getNewWeekReport(Integer userId){
		return weekReportMapper.getNewWeekReport(userId);
	}
	
	
	@Override
	public Integer updateWeekReport(WeekReport weekReport){
		return weekReportMapper.updateWeekReport(weekReport);
	}
	
	
	/**
	 * 添加周报和关键事件结果关系
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public Integer addWeekReportRel(List<WeekReportRel> list)  {
		if(list==null||list.size()<1){
			return 0;
		}
		return weekReportMapper.addWeekReportRel(list);
	}
	
	/**
	 * 删除周报和关键事件结果关系
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public Integer deleteWeekReport(Integer id)  {
		return weekReportMapper.deleteWeekReport(id);
	}
	
	/**
	 * 删除周报和关键事件结果关系
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public Integer deleteWeekReportRel(Integer weekReportId)  {
		return weekReportMapper.deleteWeekReportRel(weekReportId);
	}
	
	
	@Override
	public List<WeekReport> getWeekReportList(WeekReportQuery weekReportQuery) {
		return weekReportMapper.getWeekReportList(weekReportQuery);
	}
	
	@Override
	public List<WeekReportRel> getWeekReportRelList(WeekReportRel weekReportRel) {
		return weekReportMapper.getWeekReportRelList(weekReportRel);
	}
	
}
