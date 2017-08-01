package com.netease.learn.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.learn.dao.UserDao;
import com.netease.learn.mapper.UserMapper;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	
	@Autowired
	private UserMapper userMapper;
	
	
	
}
