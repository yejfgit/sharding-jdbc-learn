package com.netease.okr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.okr.mapper.okr.OptLogMapper;
import com.netease.okr.model.entity.OptLog;
import com.netease.okr.service.OptLogService;

@Service
public class OptLogServiceImpl implements OptLogService {

	@Autowired
	private OptLogMapper optLogMapper;
	
	@Override
	public Integer addOptLog(OptLog optLog){
		return optLogMapper.addOptLog(optLog);
	}
	
}
