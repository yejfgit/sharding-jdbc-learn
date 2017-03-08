package com.netease.okr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.dao.KeyResultDao;
import com.netease.okr.dao.ObjectivesDao;
import com.netease.okr.dao.WeekReportDao;
import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.OkrNum;
import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.OkrService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.JsonUtil;
import com.netease.okr.util.MyStringUtil;
import com.netease.okr.util.UserContextUtil;

@Service
public class OkrServiceImpl implements OkrService {

	@Autowired
	private ObjectivesDao objectivesDao;
	
	@Autowired
	private KeyResultDao keyResultDao;
	
	@Autowired
	private WeekReportDao weekReportDao;
	
	/**
	 * @author yejf
	 * @param userId
	 * @return List<Objectives>
	 * @throws DataAccessException
	 */
	@Override
	public List<Objectives> getMyOkrList(Integer userId) {
		return objectivesDao.getMyOkrList(userId);
	}
	
	

	/**
	 * @author yejf
	 * @param userId
	 * @return List<Objectives>
	 * @throws DataAccessException
	 */
	@Override
	public OkrNum getMyOkrNum(Integer userId) {
		OkrNum okrNum= new OkrNum();
		okrNum.setUserId(userId);
		
		okrNum.setOkrNum(objectivesDao.getAllMyOkrListCount(userId));
		okrNum.setWeekReportNum(weekReportDao.getWeekReportCountByUserId(userId));
		return okrNum;
	}
	
	
	
	/**
	 * @author yejf
	 * @param type,objectives
	 * @return JsonResponse
	 * @throws DataAccessException
	 */
	@Override
	public JsonResponse addObjectives(Objectives objectives) {
		User user = (User) UserContextUtil.getUserContext().getUser();
		
		int c = 0;
		
		if(objectives==null||MyStringUtil.isBlank(objectives.getType())){
			return JsonUtil.toJsonFail("【获得objectives信息错误】");
		}
		
		String type = objectives.getType();
		
		if(ConstantsUtil.OPREATE_TYPE_ADD.equals(type)){
			
			String nextNum = getNextObjectivesNum();
			//设置目标编号
			objectives.setObjectivesCode(ConstantsUtil.OBJECTIVES_CODE_PRE+nextNum);
			//设置目标名称
			objectives.setObjectivesName(ConstantsUtil.OBJECTIVES_NAME_PRE+nextNum);
			
			objectives.setUserId(user.getId());
			c = objectivesDao.addObjectives(objectives);
		}else if(ConstantsUtil.OPREATE_TYPE_UPDATE.equals(type)&&objectives.getId()!=null){
			c = objectivesDao.updateObjectives(objectives);
		}else if(ConstantsUtil.OPREATE_TYPE_DEL.equals(type)&&objectives.getId()!=null){
			//检查是否存在关键事件及结果，存在不能删除
			List<KeyResult> krs =  keyResultDao.getKeyResultListByoId(objectives.getId());
			if(krs!=null&&krs.size()>0){
				return JsonUtil.toJsonFail("【存在关键事件及结果的目标不能删除】");
			}else{
				c = objectivesDao.deleteObjectives(objectives.getId());
			}
			
		}
		
		if(c>0){
			return JsonUtil.toJsonObj(objectives);
		}else{
			return JsonUtil.toJsonFail("【操作失败type="+type+" and objectivesId="+objectives.getId()+"】");
			
		}
		
	}
	
	
	/**
	 * @author yejf
	 * @param type,keyResult
	 * @return JsonResponse
	 * @throws DataAccessException
	 */
	@Override
	public JsonResponse addKeyResult(KeyResult keyResult) {
		int c = 0;
		
		if(keyResult==null||MyStringUtil.isBlank(keyResult.getType())||(!ConstantsUtil.OPREATE_TYPE_ADD.equals(keyResult.getType())&&keyResult.getId()==null)
				||(ConstantsUtil.OPREATE_TYPE_ADD.equals(keyResult.getType())&&keyResult.getObjectivesId()==null)){
			return JsonUtil.toJsonFail("【type为空或获得关键指标信息（keyResult）错误】");
		}
		
		String type = keyResult.getType();
		
		if(ConstantsUtil.OPREATE_TYPE_ADD.equals(type)){
			
			//获取目标信息
			Objectives objectives = objectivesDao.getObjectivesById(keyResult.getObjectivesId());

			//获取下级编号
			String nextNum = getNextKeyResultNum(keyResult.getObjectivesId());
			//设置关键指标和结果编号
			keyResult.setKeyResultCode(objectives.getObjectivesCode()+ConstantsUtil.KRY_RESULT_CODE_PRE+nextNum);
			
			c = keyResultDao.addKeyResult(keyResult);
		}else if(ConstantsUtil.OPREATE_TYPE_UPDATE.equals(type)){
			c = keyResultDao.updateKeyResult(keyResult);
		}else if(ConstantsUtil.OPREATE_TYPE_DEL.equals(type)){
			//检查是否存在周报，存在不能删除
			List<WeekReport> wrs =  weekReportDao.getWeekReportListByKeyResultId(keyResult.getId());
			if(wrs!=null&&wrs.size()>0){
				return JsonUtil.toJsonFail("【存在周报的关键事件及结果不能删除】");
			}else{
				c = keyResultDao.deleteKeyResult(keyResult.getId());
			}
		}
		
		if(c>0){
			return JsonUtil.toJsonObj(keyResult);
		}else{
			return JsonUtil.toJsonFail("【addKeyResult操作失败type="+type+"】");
		}
		
	}
	
	
	/**
	 * 
	 * 获取目标当前编号
	 * */
	private String getNextObjectivesNum(){
		User user = (User) UserContextUtil.getUserContext().getUser();
		
		Integer codeNum = objectivesDao.getAllMyOkrListCount(user.getId());
		
		return (++codeNum).toString();
	}
	

	/**
	 * 
	 * 获取关键事件及结果当前编号
	 * */
	private String getNextKeyResultNum(Integer objectivesId){
		
		Integer codeNum = keyResultDao.getALLKeyResultListCountByoId(objectivesId);
		
		return (++codeNum).toString();
	}
	
}
