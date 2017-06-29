package com.netease.okr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.okr.dao.DeptDao;
import com.netease.okr.dao.SummaryDao;
import com.netease.okr.model.entity.Dept;
import com.netease.okr.model.entity.Summary;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.DeptService;
import com.netease.okr.service.SummaryService;
import com.netease.okr.util.RedisUserContextUtil;

@Service
public class SummaryServiceImpl implements SummaryService {

	@Autowired
	private SummaryDao summaryDao;
	
	
	
	
	@Override
	public List<Summary> getSummaryList(Integer userId){
		
		return summaryDao.getSummaryList(userId);
		
	}
	
	
	
	
}
