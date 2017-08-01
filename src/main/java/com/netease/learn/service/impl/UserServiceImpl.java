package com.netease.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.learn.mapper.UserMapper;
import com.netease.learn.model.User;
import com.netease.learn.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public Integer addUser(User use){
		return userMapper.addUser(use);
	}
	
	@Override
	public Integer delUser(Integer id){
		return userMapper.delUser(id);
	}
	
	@Override
	public Integer updateUser(User user){
		return userMapper.updateUser(user); 
	}
	
	@Override
	public User getUserById(Integer id){
		return userMapper.getUserById(id);
	}

}
