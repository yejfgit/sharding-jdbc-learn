package com.netease.okr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.okr.dao.SummaryOtherDao;
import com.netease.okr.mapper.okr.AppendixMapper;
import com.netease.okr.model.entity.Appendix;
import com.netease.okr.model.entity.SummaryOther;
import com.netease.okr.service.SummaryOtherService;
import com.netease.okr.util.LoggerUtil;

@Service
public class SummaryOtherServiceImpl implements SummaryOtherService {

	@Autowired
	private SummaryOtherDao summaryOtherDao;
	
	@Autowired
	private AppendixMapper appendixMapper;

	@Override
	public List<SummaryOther> getSummaryOtherList(Integer summaryId) {
		return summaryOtherDao.getSummaryOtherList(summaryId);
	}
	
	
	@Override
	public Boolean addSummaryOtherList(List<SummaryOther> summaryOtherList) {

		try {
			for(SummaryOther summaryOther:summaryOtherList){
				
				summaryOtherDao.insertSummaryOther(summaryOther);
				updateAppendixList(summaryOther.getId(),summaryOther.getAppendixList());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("addSummaryOtherList error", e);
			return false;
		}
		

		return true;
	}
	
	
	/**
	 * 更新附件周报关系信息
	 * */
	private void updateAppendixList(Integer summaryOtherId, List<Appendix> appendixList){
		
		appendixMapper.deleteAppendixRel(summaryOtherId);
		appendixMapper.addAppendixRel(appendixList);
		
	}

}
