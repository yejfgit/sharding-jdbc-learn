package com.netease.okr.mapper.okr;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.security.User;

/**
 * @Description: mapper
 * @author hzyejinfu
 * @date 2017年02月07日 下午5:35:16
 * @version V1.0   
 */

public interface RoleMapper {

	/**
	 * 查询职员信息
	 * @return
	 * @throws DataAccessException
	 */
	public User getUserById(@Param(value = "id") Integer id) throws DataAccessException;
	
	
	/**
	 * 查询职员信息
	 * @return
	 * @throws DataAccessException
	 */
	public User getUserByUsername(@Param(value = "userName") String userName) throws DataAccessException;

}
