package com.netease.okr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.dao.WeekReportDao;
import com.netease.okr.mapper.okr.AppendixMapper;
import com.netease.okr.mapper.okr.DateInfoMapper;
import com.netease.okr.model.entity.Appendix;
import com.netease.okr.model.entity.DateInfo;
import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.WeekReportRel;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.WeekReportQuery;
import com.netease.okr.service.WeekReportService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.JsonUtil;
import com.netease.okr.util.MyStringUtil;
import com.netease.okr.util.UserContextUtil;

@Service
public class WeekReportServiceImpl implements WeekReportService {

	@Autowired
	private WeekReportDao weekReportDao;
	
	@Autowired
	private AppendixMapper appendixMapper;
	
	@Autowired
	private DateInfoMapper dateInfoMapper;
	
	
	/**
	 * @author yejf
	 * @param keyResultId
	 * @return List<WeekReport>
	 * @throws DataAccessException
	 */
	@Override
	public List<WeekReport> getWeekReportListByKeyResultId(Integer keyResultId) {
		return weekReportDao.getWeekReportListByKeyResultId(keyResultId);
	}
	
	
	/**
	 * @author yejf
	 * @param WeekReportQuery
	 * @return List<WeekReport>
	 * @throws DataAccessException
	 */
	@Override
	public List<WeekReport> getWeekReportList(WeekReportQuery weekReportQuery) {
		return weekReportDao.getWeekReportList(weekReportQuery);
	}
	
	
	/**
	 * 
	 * type="release" || “save”
	 * @author yejf
	 * @param String type,List<WeekReport> weekReport
	 * @return JsonResponse
	 * @throws DataAccessException 
	 * */
	@Override
	public JsonResponse addWeekReports(String type,List<WeekReport> weekReports) {
		
		if(MyStringUtil.isNotBlank(type)&&weekReports!=null&&weekReports.size()>0){
			saveWeekReportList(type,weekReports);
		}else{
			return JsonUtil.toJsonFail("【空信息】");
		}
		
		return  JsonUtil.toJsonObj(weekReports);
	}
	
	
	/**
	 * 
	 * type="update" || “delete”
	 * @author yejf
	 * @param String type,WeekReport weekReport
	 * @return JsonResponse
	 * @throws DataAccessException 
	 * */
	@Override
	public JsonResponse updateWeekReports(String type,WeekReport weekReport) {
		
		if(MyStringUtil.isNotBlank(type)&&weekReport!=null&&weekReport.getId()!=null){
			updateWeekReportList(type,weekReport);
		}else{
			return JsonUtil.toJsonFail("【空信息】");
		}
		
		return  JsonUtil.toJsonObj(weekReport);
		
	}
	
	
	/**
	 * 更新周报周报
	 * */
	private void updateWeekReportList(String type,WeekReport weekReport){
		Integer weekReportId = weekReport.getId();
		//删除
		if(ConstantsUtil.OPREATE_TYPE_DEL.equals(type)){
			
			weekReportDao.deleteWeekReportRel(weekReportId);//删除周报
			deleteWeekReportRelList(weekReportId);//删除周报关系表
			deleteAppendixList(weekReportId);//删除周报附件表
			
		}else if(ConstantsUtil.OPREATE_TYPE_UPDATE.equals(type)){
			weekReportDao.updateWeekReport(weekReport);
			
			//保存周报关键事件关系表
			saveWeekReportRelList(weekReportId,weekReport.getKeyResultList());
			
			//更新周报附件信息
			updateAppendixList(weekReportId,weekReport.getAppendixList());
		}
		
	}
	
	
	/**
	 * 保存周报
	 * */
	private void saveWeekReportList(String type,List<WeekReport> weekReports){
		User user = (User) UserContextUtil.getUserContext().getUser();
		
		Integer status = -1;
		if(ConstantsUtil.OPREATE_TYPE_RELEASE.equals(type)&&weekReports!=null&&weekReports.size()>0){
			status = ConstantsUtil.OPREATE_TYPE_RELEASE_STATUS;
		}else if(ConstantsUtil.OPREATE_TYPE_SAVE.equals(type)&&weekReports!=null&&weekReports.size()>0){
			status = ConstantsUtil.OPREATE_TYPE_SAVE_STATUS;
		}
		
		
		for(WeekReport weekReport:weekReports){
			weekReport.setStatus(status);
			weekReport.setUserId(user.getId());
			//获取月份信息
			DateInfo dateInfo = new DateInfo();
			dateInfo.setYear(weekReport.getYear());
			dateInfo.setWeek(weekReport.getWeek());
			String month = dateInfoMapper.getMonth(dateInfo);
			
			weekReport.setMonth(month);
			if(weekReport.getId()!=null){
				weekReportDao.updateWeekReport(weekReport);
			}else{
				weekReportDao.addWeekReport(weekReport);
			}
			
			
			//保存周报关键事件关系表
			saveWeekReportRelList(weekReport.getId(),weekReport.getKeyResultList());
			
			//更新周报附件信息
			updateAppendixList(weekReport.getId(),weekReport.getAppendixList());
			
			
		}
	}
	
	/**
	 * 保存周报关系
	 * */
	private void saveWeekReportRelList(Integer weekReportId, List<KeyResult> keyResults){
		List<WeekReportRel> weekReportRels = new ArrayList<WeekReportRel>();
		if(keyResults!=null&&keyResults.size()>0){
			for(KeyResult keyResult:keyResults){
				WeekReportRel wrr = new WeekReportRel();
				wrr.setKeyResultId(keyResult.getId());
				wrr.setWeekReportId(weekReportId);
				weekReportRels.add(wrr);
			}
			
		}
		
		deleteWeekReportRelList(weekReportId);
		
		weekReportDao.addWeekReportRel(weekReportRels);
		
	}
	
	/**
	 * 删除周报关系
	 * */
	private Integer deleteWeekReportRelList(Integer weekReportId){

		return weekReportDao.deleteWeekReportRel(weekReportId);
		
	}
	
	/**
	 * 更新附件周报关系信息
	 * */
	private void updateAppendixList(Integer weekReportId, List<Appendix> appendixList){
		List<Appendix> appendixs = new ArrayList<Appendix>();
		if(appendixList!=null&&appendixList.size()>0){
			for(Appendix appendix:appendixList){
				appendix.setWeekReportId(weekReportId);
				appendixs.add(appendix);
			}
			
		}
		appendixMapper.updateAppendix(appendixs);
		
	}
	
	/**
	 * 删除附件信息
	 * */
	private Integer deleteAppendixList(Integer weekReportId){

		return appendixMapper.deleteAppendixByWeekReportId(weekReportId);
		
	}

}
