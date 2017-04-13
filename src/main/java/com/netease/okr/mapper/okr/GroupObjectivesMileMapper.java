package com.netease.okr.mapper.okr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.GroupObjectivesMile;

/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface GroupObjectivesMileMapper {
	


	public List<GroupObjectivesMile> getGroupObjectivesMiles(@Param(value = "groupObjectivesId") Integer groupObjectivesId)  throws DataAccessException;

		
	public Integer addGroupObjectivesMile(GroupObjectivesMile groupObjectivesMile)  throws DataAccessException;
	

	public Integer deleteGroupObjectivesMile(@Param(value = "groupObjectivesId") Integer groupObjectivesId)  throws DataAccessException;
	
	public Integer deleteGroupObjectivesMileById(Integer id)  throws DataAccessException;
	

	public Integer updateGroupObjectivesMile(GroupObjectivesMile groupObjectivesMile)  throws DataAccessException;
	
	
}
