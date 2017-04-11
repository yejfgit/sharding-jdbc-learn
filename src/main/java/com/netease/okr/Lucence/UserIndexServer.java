package com.netease.okr.Lucence;

import com.netease.okr.common.PageBean;
import com.netease.okr.common.PageJsonResponse;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.UserQuery;

public interface UserIndexServer {

	public void buildUserIndex() throws Exception;

	public PageJsonResponse<User> searchUser(UserQuery userQuery, PageBean<User> page);

}
