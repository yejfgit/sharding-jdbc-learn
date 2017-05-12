package com.netease.okr.service.impl;


import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.okr.dao.ObjectivesDao;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.service.ObjectivesService;

@Repository
public class ObjectivesServiceImpl extends SqlSessionDaoSupport implements ObjectivesService {
	
	@Autowired
	private ObjectivesDao objectivesDao;

	@Override
	public Objectives getObjectivesById(Integer id) {
		return objectivesDao.getObjectivesById(id);
	}
	
}
