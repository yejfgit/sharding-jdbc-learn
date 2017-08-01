package com.netease.learn.service;

import com.netease.learn.model.User;

public interface UserService {

	public Integer updateUser(User user);

	public Integer delUser(Integer id);

	public Integer addUser(User use);

	public User getUserById(Integer id);

	
}
