package com.netease.okr.mapper.okr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.GroupObjectivesUser;

/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface GroupObjectivesUserMapper {
	


	public List<GroupObjectivesUser> getGroupObjectivesUsers(@Param(value = "groupObjectivesId") Integer groupObjectivesId)  throws DataAccessException;

		
	public Integer addGroupObjectivesUser(GroupObjectivesUser groupObjectivesUser)  throws DataAccessException;
	

	public Integer deleteGroupObjectivesUser(Integer id)  throws DataAccessException;
	
	
}
