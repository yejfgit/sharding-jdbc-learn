package com.netease.okr.mapper.okr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.Appendix;

/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface AppendixMapper {
	
	
	/**
	 * @author yejf
	 * @param weekReportId
	 * @return List<Appendix>
	 * @throws DataAccessException
	 */
	public List<Appendix> getAppendixByWeekReportId(@Param(value = "weekReportId") Integer weekReportId)  throws DataAccessException;
	
	
	public List<Appendix> getAppendixRelList(@Param(value = "relId") Integer relId,@Param(value = "type") Integer type)  throws DataAccessException;
	
	public List<Appendix> getAppendixRelListOfSummaryOther(@Param(value = "summaryOtherId") Integer summaryOtherId)  throws DataAccessException;
	
	public List<Appendix> getAppendixRelListOfObjectivesSummary(@Param(value = "objectivesSummaryId") Integer objectivesSummaryId)  throws DataAccessException;
	
	/**
	 * @author yejf
	 * @param id
	 * @return Appendix
	 * @throws DataAccessException
	 */
	public Appendix getAppendix(@Param(value = "id") Integer id)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param Appendix
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer addAppendix(Appendix appendix)  throws DataAccessException;
	
	public Integer addAppendixRel(@Param(value = "list") List<Appendix> list)  throws DataAccessException;
	
	/**
	 * @author yejf
	 * @param id
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer deleteAppendix(@Param(value = "id") Integer id)  throws DataAccessException;
	
	public Integer deleteAppendixRel(@Param(value = "relId") Integer relId,@Param(value = "type") Integer type)  throws DataAccessException;
	
	public Integer deleteAppendixRelById(@Param(value = "id") Integer id)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param weekReportId
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer deleteAppendixByWeekReportId(@Param(value = "weekReportId") Integer weekReportId)  throws DataAccessException;
	
	
	
	/**
	 * 更新周报附件信息
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer updateAppendix(List<Appendix> appendixList)  throws DataAccessException;
	
	
	
	
	
}
