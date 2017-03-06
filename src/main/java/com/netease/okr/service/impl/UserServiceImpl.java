package com.netease.okr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.okr.common.PageBean;
import com.netease.okr.dao.UserDao;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.UserQuery;
import com.netease.okr.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUserByUNo(String uNo){
		return userDao.getUserByUNo(uNo);
	}
	
	@Override
	public User getUserByUsername(String userName, boolean isLock) {
		return userDao.getUserByUsername(userName, isLock);
	}
	
	@Override
	public User getUserByEmail(String corpMail) {
		return userDao.getUserByEmail(corpMail);
	}
	
	@Override
	public Integer addUser(User user){
		return userDao.addUser(user);
	}
	
	@Override
	public List<User> getUserListOfDept(String deptId){
		return userDao.getUserListOfDept(deptId);
	}
	
	@Override
	public Integer updateUser(User user){
		return userDao.updateUser(user);
	}
	
	
	@Override
	public List<User> getUsers(UserQuery user,PageBean<User> pageBean){
		
		return userDao.getUsers(user, pageBean);
		
	}

}
