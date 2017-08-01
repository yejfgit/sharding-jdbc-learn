package com.netease.learn.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.netease.learn.model.User;

public interface UserMapper {

	public Integer addUser(User use)  throws DataAccessException;
	
	public Integer delUser(@Param(value = "id") Integer id)  throws DataAccessException;
	
	public Integer updateUser(User user)  throws DataAccessException;
	
	public User getUserById(@Param(value = "id") Integer id)  throws DataAccessException;
}
