package com.netease.okr.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.common.PageBean;
import com.netease.okr.common.PageJsonResponse;
import com.netease.okr.dao.UserDao;
import com.netease.okr.mapper.okr.UserMapper;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.UserQuery;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public User getUserByUNo(String uNo){
		return userMapper.getUserByUNo(uNo);
	}
	
	@Override
	public User getUserByUsername(String userName, boolean isLock) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", userName);
		map.put("isLock", isLock);
		return userMapper.getUserByUsername(map);
	}
	
	@Override
	public User getUserByEmail(String corpMail){
		return userMapper.getUserByEmail(corpMail);
	}
	
	
	@Override
	public List<User> getUsers(UserQuery user){
		
		List<User> userList = sqlSessionTemplate.selectList("getUsers",user);

		return userList;
		
	}
	
	@Override
	public PageJsonResponse<User> getUsersPage(UserQuery user,PageBean<User> pageBean){
		
		PageJsonResponse<User> pageRes = new PageJsonResponse<User>();
		pageRes.setPage(pageBean);
		
		List<User> userList = new ArrayList<User>();
		if (pageBean != null && pageBean.getPageSize() != -1) {
			pageBean.setTotalResults(userMapper.getUsersCount(user));
			int offset = pageBean.getStartRownum() - 1;
			int limit = pageBean.getPageSize();
			userList = sqlSessionTemplate.selectList("getUsers",user, new RowBounds(offset, limit));
		} 
		pageRes.setObjList(userList);
		return pageRes;
		
	}
	
	
	
	
	@Override
	public List<User> getUserListOfDept(String deptId){
		return userMapper.getUserListOfDept(deptId);
	}
	
	@Override
	public Integer addUser(User user){
		return userMapper.addUser(user);
	}
	
	@Override
	public Integer updateUser(User user){
		return userMapper.updateUser(user);
	}
	
}
