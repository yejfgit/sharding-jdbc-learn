package com.netease.okr.mapper.okr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.WeekReportRel;

/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface WeekReportMapper {
	
	/**
	 * 添加周报
	 * @return
	 * @throws DataAccessException
	 */
	public Integer addWeekReport(WeekReport weekReport)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param keyResultId
	 * @return List<WeekReport>
	 * @throws DataAccessException
	 */
	public List<WeekReport> getWeekReportListByKeyResultId(@Param(value = "keyResultId") Integer keyResultId) ;
	
	/**
	 * 修改周报
	 * @return
	 * @throws DataAccessException
	 */
	public Integer updateWeekReport(WeekReport weekReport)  throws DataAccessException;
	
	/**
	 * 添加周报和关键事件结果关系
	 * @return
	 * @throws DataAccessException
	 */
	public Integer addWeekReportRel(List<WeekReportRel> weekReportRelList)  throws DataAccessException;
	
	
	/**
	 * 删除周报和关键事件结果关系
	 * @return
	 * @throws DataAccessException
	 */
	public Integer deleteWeekReportRel(@Param(value = "weekReportId") Integer weekReportId)  throws DataAccessException;
	
	/**
	 * 查询用户周报数量
	 * @returnInteger
	 * @throws DataAccessException
	 */
	public Integer getWeekReportCountByUserId(Integer userId);
	
	
}
