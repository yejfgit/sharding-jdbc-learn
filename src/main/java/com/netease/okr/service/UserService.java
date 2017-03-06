package com.netease.okr.service;

import java.util.List;

import com.netease.okr.common.PageBean;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.UserQuery;

public interface UserService {

	public User getUserByUsername(String userName, boolean isLock);

	public User getUserByEmail(String corpMail);

	public Integer addUser(User user);

	public User getUserByUNo(String uNo);

	public Integer updateUser(User user);

	public List<User> getUserListOfDept(String deptId);

	public List<User> getUsers(UserQuery user, PageBean<User> pageBean);
}
