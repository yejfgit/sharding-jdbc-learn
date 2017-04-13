package com.netease.okr.mapper.okr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.security.Role;

/**
 * @Description: mapper
 * @author hzyejinfu
 * @date 2017年02月07日 下午5:35:16
 * @version V1.0   
 */

public interface RoleMapper {

	/**
	 * 查询角色信息
	 * @return
	 * @throws DataAccessException
	 */
	public List<Role> getRolesByUserId(@Param(value = "userId") Integer userId) throws DataAccessException;
	


}
