package com.netease.okr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.netease.okr.dao.KeyResultDao;
import com.netease.okr.dao.ObjectivesDao;
import com.netease.okr.dao.SummaryDao;
import com.netease.okr.enums.AppendixTypeEnum;
import com.netease.okr.enums.DateModelEnum;
import com.netease.okr.mapper.okr.DateInfoMapper;
import com.netease.okr.model.entity.DateInfo;
import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.Summary;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.AppendixService;
import com.netease.okr.service.SummaryOtherService;
import com.netease.okr.service.SummaryService;
import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.RedisUserContextUtil;

@Service
public class SummaryServiceImpl implements SummaryService {

	@Autowired
	private SummaryDao summaryDao;
	
	@Autowired
	private DateInfoMapper dateInfoMapper;
	
	@Autowired
	private SummaryOtherService summaryOtherService;
	
	@Autowired
	private AppendixService appendixService;
	
	@Autowired
	private ObjectivesDao objectivesDao;
	
	@Autowired
	private KeyResultDao keyResultDao;
	
	
	
	@Override
	public List<Summary> getSummaryList(Integer userId){
		
		return summaryDao.getSummaryList(userId);
		
	}
	
	
	@Override
	public Summary getSummaryById(Integer id){
		
		return summaryDao.selectById(id);
		
	}
	
	@Override
	public Integer getSummaryCountOfDate(Integer dateTabId){
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		return summaryDao.getSummaryCountOfDate(dateTabId, user.getId());
		
	}
	
	@Override
	public Boolean addSummary(Summary summary){
		
		if(setSummaryData(summary)){
			int c = summaryDao.insertSummary(summary);
			
			if(c>0){
				//添加其他总结
				summaryOtherService.addSummaryOtherList(summary.getId(),summary.getSummaryOtherList());
				
				//更新目标
				List<Objectives> objectivesList = summary.getObjectivesList();
				if(objectivesList!=null&&objectivesList.size()>0){
					updateObjectivesSummary(summary,objectivesList);
				}
				
			}
		}else{
			return false;
		}
		
		return true;
		
	}
	
	
	@Override
	public Boolean updateSummary(Summary summary){
		
		if(summary!=null&&summary.getId()!=null){
			int c = summaryDao.updateById(summary);
			
			if(c>0){
				//更新其他总结
				summaryOtherService.updateSummaryOtherList(summary.getId(),summary.getSummaryOtherList());
				
				//更新目标
				updateObjectivesSummary(summary,summary.getObjectivesList());
				
			}
		}else{
			return false;
		}
		
		return true;
		
	}
	
	@Override
	public Boolean delSummary(Integer summaryId){
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		Integer userId = user.getId();
		if(summaryId!=null){
			int c = summaryDao.deleteById(summaryId);
			
			if(c>0){
				//删除其他总结
				summaryOtherService.delSummaryOtherList(summaryId);
				//删除目标总结
				objectivesDao.deleteAllObjectivesSummaryOfUser(userId);
				//删除关键结果评分
				keyResultDao.deleteAllKeyResultScoreOfUser(userId);
				//删除目标附件信息
				delObjectivesSummaryAppendix(userId);
			}
		}else{
			return false;
		}
		
		return true;
		
	}
	
	//删除我的总结目标内容附件信息
	private Boolean delObjectivesSummaryAppendix(Integer userId){
		try {
			List<Objectives> objectivesList = objectivesDao.getMyOkrList(userId);
			
			for(Objectives objectives:objectivesList){
				if(objectives.getId()==null) {
					LoggerUtil.info("删除我的总结目标内容附件信息未获取ID:"+JSON.toJSONString(objectives));
				}
				appendixService.deleteAppendixList(objectives.getId(), AppendixTypeEnum.TYPE1.getId());
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error(" delObjectivesSummaryAppendix error ", e);
			return false;
		}
		
		
		return true;
		
	}
	
	
	//更新我的总结目标内容
	private void updateObjectivesSummary(Summary summary,List<Objectives> objectivesList){
		
		if(objectivesList!=null&&objectivesList.size()>0){
			for(Objectives objectives:objectivesList){
				if(objectives.getId()==null) {
					LoggerUtil.info("添加我的总结目标未获取ID:"+JSON.toJSONString(objectives));
					continue;
				}
				objectivesDao.updateObjectives(objectives);
				
				//更新关键结果
				updateKeyResultSummary(summary,objectives.getKeyResultList());
				
				appendixService.updateAppendixList(objectives.getId(),AppendixTypeEnum.TYPE1.getId(),objectives.getAppendixList());
			}
		}
		
	}
	
	//更新我的总结关键结果得分
	private void updateKeyResultSummary(Summary summary,List<KeyResult> keyResultList){
		
		//更新关键结果
		if(keyResultList!=null&&keyResultList.size()>0){
			for(KeyResult keyResult:keyResultList){
				if(keyResult.getId()==null) {
					LoggerUtil.info("添加我的总结关键结果未获取ID:"+JSON.toJSONString(keyResult));
					continue;
				}
				keyResultDao.updateKeyResult(keyResult);
			}
		}
		
	}
		
	
	//组装我的总结数据
	private Boolean setSummaryData(Summary summary){
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		DateInfo dateInfo = dateInfoMapper.getClassDateById(summary.getDateTabId());
		
		if(dateInfo==null){
			LoggerUtil.info("添加我的总结找不到时间数据");
			return false;
		}
		summary.setDateTabId(dateInfo.getId());
		summary.setUserId(user.getId());
		
		summary.setName(DateModelEnum.getRemarkOfId(dateInfo.getType())+"-"+dateInfo.getYear()+"年"+dateInfo.getDateClass());
		
		return true;
		
	}
	
	
	
	
}
