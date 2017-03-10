package com.netease.okr.mapper.okr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.KeyResult;

/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface KeyResultMapper {
	
	
	/**
	 * @author yejf
	 * @param objectivesId
	 * @return List<KeyResult>
	 * @throws DataAccessException
	 */
	public List<KeyResult> getKeyResultListByoId(@Param(value = "objectivesId") Integer objectivesId)  throws DataAccessException;
	

	/**
	 * @author yejf
	 * @param objectivesId
	 * @return List<KeyResult>
	 * @throws DataAccessException
	 */
	public List<KeyResult> getKeyResultListByWeekReportId(@Param(value = "weekReportId") Integer weekReportId)  throws DataAccessException;
	
	
	
	/**
	 * @author yejf
	 * @param objectivesId
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getNextCodeNum(@Param(value = "objectivesId") Integer objectivesId)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param id
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer deleteKeyResult(Integer id)  throws DataAccessException;
	
	
	
	/**
	 * @author yejf
	 * @param keyResult
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer addKeyResult(KeyResult keyResult)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param keyResult
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer updateKeyResult(KeyResult keyResult)  throws DataAccessException;
	
	/**
	 * 更新关键事件状态已开始
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer updateKeyResultStatus(List<KeyResult> keyResults)  throws DataAccessException;
	
}
