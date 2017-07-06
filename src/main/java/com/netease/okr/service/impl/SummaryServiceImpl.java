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
import com.netease.okr.model.entity.ObjectivesSummary;
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
				updateObjectivesSummary(summary.getId(),summary.getObjectivesList());
				
			}
		}else{
			return false;
		}
		
		return true;
		
	}
	
	
	@Override
	public Boolean updateSummary(Summary summary){
		

		if(summary!=null&&summary.getId()!=null&&setSummaryData(summary)){
			int c = summaryDao.updateById(summary);
			
			if(c>0){
				//更新其他总结
				summaryOtherService.updateSummaryOtherList(summary.getId(),summary.getSummaryOtherList());
				
				//更新目标
				updateObjectivesSummary(summary.getId(),summary.getObjectivesList());
				
			}
		}else{
			return false;
		}

		
		return true;
		
	}
	
	@Override
	public Boolean delSummary(Integer summaryId){
		if(summaryId!=null){
			int c = summaryDao.deleteById(summaryId);
			
			if(c>0){
				//删除其他总结
				summaryOtherService.delSummaryOtherList(summaryId);

				//删除关键结果评分
				keyResultDao.deleteKeyResultScore(summaryId);
				//删除目标附件信息
				delObjectivesSummaryAppendix(summaryId);
				//删除目标总结
				objectivesDao.deleteObjectivesSummary(summaryId);
			}
		}else{
			return false;
		}

		
		
		return true;
		
	}
	
	//删除我的总结目标内容附件信息
	private Boolean delObjectivesSummaryAppendix(Integer summaryId){

		List<ObjectivesSummary> objectivesSummarys = objectivesDao.getObjectivesSummarylistBySummaryId(summaryId);
		
		for(ObjectivesSummary objectivesSummary:objectivesSummarys){
			if(objectivesSummary.getId()==null) {
				LoggerUtil.info("删除我的总结目标内容附件信息未获取ID:"+JSON.toJSONString(objectivesSummary));
			}
			appendixService.deleteAppendixList(objectivesSummary.getId(), AppendixTypeEnum.TYPE1.getId());
		}
		
		return true;
		
	}
	
	
	//更新我的总结目标内容
	private void updateObjectivesSummary(Integer summaryId,List<Objectives> objectivesList){

		if(objectivesList!=null&&objectivesList.size()>0){
			//清除数据再保存
			objectivesDao.deleteObjectivesSummary(summaryId);
			keyResultDao.deleteKeyResultScore(summaryId);
			
			//保存目标总结和关键结果得分
			for(Objectives objectives:objectivesList){
				if(objectives.getId()==null) {
					LoggerUtil.info("添加我的总结目标未获取ID:"+JSON.toJSONString(objectives));
					continue;
				}
				ObjectivesSummary objectivesSummary = objectivesDao.updateObjectivesSummary(summaryId,objectives);
				if(objectivesSummary!=null&&objectivesSummary.getId()!=null){
					appendixService.updateAppendixList(objectivesSummary.getId(),AppendixTypeEnum.TYPE1.getId(),objectives.getAppendixList());
				}

				//更新关键结果
				updateKeyResultSummary(summaryId,objectives.getKeyResultList());
				
				
			}
		}
		
	}
	
	//更新我的总结关键结果得分
	private void updateKeyResultSummary(Integer summaryId,List<KeyResult> keyResultList){
		
		//更新关键结果
		if(keyResultList!=null&&keyResultList.size()>0){
			for(KeyResult keyResult:keyResultList){
				if(keyResult.getId()==null) {
					LoggerUtil.info("添加我的总结关键结果未获取ID:"+JSON.toJSONString(keyResult));
					continue;
				}
				keyResultDao.updateKeyResultScore(summaryId,keyResult);
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
		summary.setUserId(user.getId());
		
		summary.setName(DateModelEnum.getRemarkOfId(dateInfo.getType())+"-"+dateInfo.getYear()+"年"+dateInfo.getDateClass());
		
		return true;
		
	}
	
	
	
	
}
