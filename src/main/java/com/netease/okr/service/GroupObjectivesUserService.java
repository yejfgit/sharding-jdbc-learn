package com.netease.okr.service;

import java.util.List;

import com.netease.okr.model.entity.GroupObjectivesUser;

public interface GroupObjectivesUserService {

	public List<GroupObjectivesUser> getGroupObjectivesUsers(Integer groupObjectivesId);

	public Integer addGroupObjectivesUser(GroupObjectivesUser groupObjectivesUser);

	public Integer deleteGroupObjectivesUser(Integer id);


}
