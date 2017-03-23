package com.netease.okr.mapper.okr;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.UserQuery;

/**
 * @Description: mapper
 * @author yejf
 * @date 20170220
 */

public interface UserMapper {

	/**
	 * 查询职员信息
	 * @return
	 * @throws DataAccessException
	 */
	public User getUserByUNo(@Param(value = "uNo") String uNo) throws DataAccessException;
	
	/**
	 * 查询部门下用户
	 * @return
	 * @throws DataAccessException
	 */
	public List<User> getUserListOfDept(String deptId) throws DataAccessException;
	
	
	/**
	 * 查询职员信息
	 * @return User
	 * @throws DataAccessException
	 */
	public User getUserByUsername(Map<String, Object> map) throws DataAccessException;
	
	/**
	 * 查询职员信息
	 * @param corpMail
	 * @return User
	 * @throws DataAccessException
	 */
	public User getUserByEmail(@Param(value = "corpMail") String corpMail) throws DataAccessException;
	
	
	/**
	 * 添加用户
	 * @param User
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer addUser(User user) throws DataAccessException;
	
	
	/**
	 * 搜索用户总条数
	 * @param UserQuery
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getUsersCount(UserQuery user) throws DataAccessException;
	
	/**
	 * 更新用户
	 * @param User
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer updateUser(User user) throws DataAccessException;
	
	
	public User getUserById(Integer id) throws DataAccessException;
	
}
