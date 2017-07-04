package com.netease.okr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.okr.mapper.okr.AppendixMapper;
import com.netease.okr.model.entity.Appendix;
import com.netease.okr.service.AppendixService;
import com.netease.okr.service.FileService;
import com.netease.okr.util.LoggerUtil;

@Service
public class AppendixServiceImpl implements AppendixService {

	@Autowired
	private AppendixMapper appendixMapper;
	
	@Autowired
	private FileService fileService;
	
	
	/**
	 * 添加附件
	 * */
	@Override
	public Boolean updateAppendixList(Integer relId,Integer type, List<Appendix> appendixList){
		try {
			
			if(appendixList==null||appendixList.size()<1) {
				LoggerUtil.info("附件关联信息为空");
				return false;
			}
			
			//设置信息
			for(Appendix appendix:appendixList){
				appendix.setAppendixId(appendix.getId());
				appendix.setType(type);
				appendix.setRelId(relId);
			}
			
			appendixMapper.deleteAppendixRel(relId,type);
			appendixMapper.addAppendixRel(appendixList);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("添加附件异常", e);
			return false;
		}
		
		
	}
	
	
	/**
	 * 删除附件
	 * */
	@Override
	public Boolean deleteAppendixList(Integer relId,Integer type){
		try {
			
			List<Appendix> appendixList = appendixMapper.getAppendixRelList(relId,type);
			
			if(appendixList!=null&&appendixList.size()>0){
				for(Appendix appendix:appendixList){
					appendixMapper.deleteAppendixRelById(appendix.getId());
					fileService.delete(appendix.getAppendixId());
				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("添加附件异常", e);
			return false;
		}
		
		
	}

}
