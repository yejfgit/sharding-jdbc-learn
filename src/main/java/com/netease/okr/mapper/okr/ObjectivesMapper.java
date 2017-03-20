package com.netease.okr.mapper.okr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.Objectives;

/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface ObjectivesMapper {
	
	/**
	 * @author yejf
	 * @param userId
	 * @return List<Objectives>
	 * @throws DataAccessException
	 */
	public List<Objectives> getMyOkrList(@Param(value = "userId") Integer userId)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param id
	 * @return Objectives
	 * @throws DataAccessException
	 */
	public Objectives getObjectivesById(@Param(value = "id") Integer id)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param userId
	 * @return id
	 * @throws DataAccessException
	 */
	public Objectives getObjectivesInfoById(@Param(value = "id") Integer id)  throws DataAccessException;
	
	
	
	
	/**
	 * @author yejf
	 * @param Objectives
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer addObjectives(Objectives objectives)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param id
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer deleteObjectives(Integer id)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param id
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getNextCodeNum(@Param(value = "userId") Integer userId)  throws DataAccessException;
	
	

	/**
	 * @author yejf
	 * @param id
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getObjectivesCount(@Param(value = "userId") Integer userId)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param Objectives
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer updateObjectives(Objectives objectives)  throws DataAccessException;
	
	
}
