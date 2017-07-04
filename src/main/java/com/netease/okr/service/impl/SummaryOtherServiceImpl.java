package com.netease.okr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.okr.dao.SummaryOtherDao;
import com.netease.okr.enums.AppendixTypeEnum;
import com.netease.okr.model.entity.SummaryOther;
import com.netease.okr.service.AppendixService;
import com.netease.okr.service.SummaryOtherService;
import com.netease.okr.util.LoggerUtil;

@Service
public class SummaryOtherServiceImpl implements SummaryOtherService {

	@Autowired
	private SummaryOtherDao summaryOtherDao;
	
	@Autowired
	private AppendixService appendixService;
	
	@Override
	public List<SummaryOther> getSummaryOtherList(Integer summaryId) {
		return summaryOtherDao.getSummaryOtherList(summaryId);
	}
	
	
	@Override
	public Boolean addSummaryOtherList(Integer summaryId,List<SummaryOther> summaryOtherList) {

		if(summaryOtherList==null||summaryOtherList.size()<1) return false;
		try {
			for(SummaryOther summaryOther:summaryOtherList){
				
				summaryOther.setSummaryId(summaryId);
				summaryOtherDao.insertSummaryOther(summaryOther);
				appendixService.updateAppendixList(summaryOther.getId(),AppendixTypeEnum.TYPE2.getId(),summaryOther.getAppendixList());
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("addSummaryOtherList error", e);
			return false;
		}
		

		return true;
	}
	
	
	@Override
	public Boolean updateSummaryOtherList(Integer summaryId,List<SummaryOther> summaryOtherList) {

		if(summaryOtherList==null||summaryOtherList.size()<1) return false;
		try {
			for(SummaryOther summaryOther:summaryOtherList){
				if(summaryOther.getId()!=null){
					summaryOtherDao.updateById(summaryOther);
				}else{
					summaryOther.setSummaryId(summaryId);
					summaryOtherDao.insertSummaryOther(summaryOther);
				}
				
				appendixService.updateAppendixList(summaryOther.getId(),AppendixTypeEnum.TYPE2.getId(),summaryOther.getAppendixList());
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("updateSummaryOtherList error", e);
			return false;
		}
		

		return true;
	}
	
	
	@Override
	public Boolean delSummaryOtherList(Integer summaryId) {

		if(summaryId==null) {
			LoggerUtil.info("delSummaryOtherList summaryId null");
			return false;
		}
		try {
			List<SummaryOther> summaryOtherList = summaryOtherDao.getSummaryOtherList(summaryId);
			if(summaryOtherList!=null&&summaryOtherList.size()>0){
				for(SummaryOther summaryOther:summaryOtherList){
					appendixService.deleteAppendixList(summaryOther.getId());
				}
			}
			
			summaryOtherDao.deleteBySummaryId(summaryId);
			
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("updateSummaryOtherList error", e);
			return false;
		}
		

		return true;
	}
	
	
	@Override
	public Boolean delSummaryOtherById(Integer id) {

		if(id==null) {
			LoggerUtil.info("delSummaryOtherById id null");
			return false;
		}
		try {
			appendixService.deleteAppendixList(id);
			summaryOtherDao.deleteById(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("delSummaryOtherById error", e);
			return false;
		}
		

		return true;
	}
	
	
	

}
