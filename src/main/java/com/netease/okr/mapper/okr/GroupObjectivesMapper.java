package com.netease.okr.mapper.okr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.GroupObjectives;
import com.netease.okr.model.entity.GroupObjectivesRel;

/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface GroupObjectivesMapper {
	

	
	/**
	 * @author yejf
	 * @param id
	 * @return Objectives
	 * @throws DataAccessException
	 */
	public List<GroupObjectives> getGroupObjectivesList(@Param(value = "deptL1Id") String deptL1Id)  throws DataAccessException;

	
	
	/**
	 * @author yejf
	 * @param Objectives
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer addGroupObjectives(GroupObjectives groupObjectives)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param id
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer deleteGroupObjectives(Integer id)  throws DataAccessException;
	
	public Integer getObjectivesList(Integer id)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param id
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getNextCodeNum(@Param(value = "deptL1Id") String deptL1Id)  throws DataAccessException;
	
	
	/**
	 * @author yejf
	 * @param Objectives
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer updateGroupObjectives(GroupObjectives groupObjectives)  throws DataAccessException;
	
	public Integer addGroupObjectivesRel(@Param(value = "list") List<GroupObjectivesRel> list)  throws DataAccessException;
	
	public Integer deleteGroupObjectivesRel(@Param(value = "groupObjectivesId") Integer groupObjectivesId)  throws DataAccessException;
	
	
}
