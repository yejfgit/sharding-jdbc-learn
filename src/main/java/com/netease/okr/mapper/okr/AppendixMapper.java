package com.netease.okr.mapper.okr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.Appendix;
import com.netease.okr.model.entity.AppendixRel;
import com.netease.okr.model.entity.WeekReportRel;

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
	
	public Integer deleteAppendixRel(@Param(value = "relId") Integer relId)  throws DataAccessException;
	
	
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
