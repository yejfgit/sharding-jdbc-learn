package com.netease.okr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.dao.KeyResultDao;
import com.netease.okr.dao.ObjectivesDao;
import com.netease.okr.dao.WeekReportDao;
import com.netease.okr.enums.KeyResultStatusEnum;
import com.netease.okr.mapper.okr.AppendixMapper;
import com.netease.okr.mapper.okr.DateInfoMapper;
import com.netease.okr.model.entity.Appendix;
import com.netease.okr.model.entity.DateInfo;
import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.WeekReportList;
import com.netease.okr.model.entity.WeekReportRel;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.WeekReportQuery;
import com.netease.okr.quartz.job.UpdateKeyResultStatusTask;
import com.netease.okr.quartz.scheduler.TaskScheduler;
import com.netease.okr.service.FileService;
import com.netease.okr.service.WeekReportService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.JsonUtil;
import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.MyDateUtils;
import com.netease.okr.util.MyStringUtil;
import com.netease.okr.util.RedisUserContextUtil;

@Service
public class WeekReportServiceImpl implements WeekReportService {

	@Autowired
	private WeekReportDao weekReportDao;
	
	
	@Autowired
	private ObjectivesDao objectivesDao;
	
	@Autowired
	private AppendixMapper appendixMapper;
	
	@Autowired
	private DateInfoMapper dateInfoMapper;

	@Autowired
	private FileService fileService;

	@Autowired
	private KeyResultDao keyResultDao;
	
	@Autowired
	private UpdateKeyResultStatusTask updateKeyResultStatusTask;
	
	
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
		List<WeekReport> weekReports =  weekReportDao.getWeekReportList(weekReportQuery);
		
		//周报列表添加目标信息
		if(weekReports!=null&&weekReports.size()>0){
			for(WeekReport wr:weekReports){
				//关键事件
				List<KeyResult> keyResults = wr.getKeyResultList();
				if(keyResults!=null&&keyResults.size()>0){
					for(KeyResult kr:keyResults){
						//添加目标信息
						kr.setObjectives(objectivesDao.getObjectivesInfoById(kr.getObjectivesId()));
					}
				}
			}
		}
		
		return weekReports;
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
	public JsonResponse addWeekReports(WeekReportList weekReportList) {
		
		if(weekReportList!=null&&weekReportList.getWeekList()!=null&&weekReportList.getWeekList().size()>0){
			saveWeekReportList(weekReportList);
		}else{
			return JsonUtil.toJsonFail("传值错误！周报内容为空");
		}
		
		return  JsonUtil.toJsonObj(toWeekReportList(weekReportList));
	}
	
	
	private WeekReportList toWeekReportList(WeekReportList weekReportList){
		
		WeekReportList wrList = new WeekReportList();
		
		if(weekReportList!=null&&weekReportList.getWeekList()!=null&&weekReportList.getWeekList().size()>0){
			List<WeekReport> weekReports = weekReportList.getWeekList();
			List<WeekReport> weekReportsAdd = new ArrayList<WeekReport>();
			for(WeekReport wr:weekReports){
				weekReportsAdd.add(weekReportDao.getWeekReportById(wr.getId()));
			}
			wrList.setWeekList(weekReportsAdd);
		}
		return wrList;
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
	public JsonResponse updateWeekReports(WeekReport weekReport) {
		
		if(weekReport!=null&&weekReport.getId()!=null&&MyStringUtil.isNotBlank(weekReport.getType())){
			//删除
			if(ConstantsUtil.OPREATE_TYPE_DEL.equals(weekReport.getType())){
				
				deleteWeekReport(weekReport.getId());
				
			}else if(ConstantsUtil.OPREATE_TYPE_UPDATE.equals(weekReport.getType())){
				
				updateWeekReport(weekReport);
				
			}
			
			
		}else{
			return JsonUtil.toJsonFail("传值错误！id或type未提交");
		}
		
		return  JsonUtil.toJsonObj(weekReport);
		
	}
	
	
	
	/**
	 * 保存周报
	 * */
	private void saveWeekReportList(WeekReportList weekReportList){
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		Integer status = -1;
		String type = weekReportList.getType();
		List<WeekReport> weekReports = weekReportList.getWeekList();
		
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
			Integer dateId = dateInfoMapper.getDateId(dateInfo);
			
			weekReport.setDateId(dateId);
			if(weekReport.getId()!=null){
				weekReportDao.updateWeekReport(weekReport);
			}else{
				weekReportDao.addWeekReport(weekReport);
			}
			
			LoggerUtil.info("创建周报【id="+weekReport.getId()+";dateId="+dateId+";year="+weekReport.getYear()+";week="+weekReport.getWeek()+"】");
			
			//在删除之前获取之前关键事件关系
			List<Integer> keyResultIds = getKeyResultIds(weekReport.getId(),weekReport.getKeyResultList());
			//保存周报关键事件关系表
			saveWeekReportRelList(weekReport,weekReport.getKeyResultList());
			
			//更新周报附件信息
			updateAppendixList(weekReport.getId(),weekReport.getAppendixList());
			
			//检查更新关键事件进行状态
			TaskScheduler.scheduleTaskAt(updateKeyResultStatusTask, MyDateUtils.addSeconds(new Date(), 2), keyResultIds,null, null);
		}
	}
	
	/**
	 * 保存周报关系
	 * */
	private void saveWeekReportRelList(WeekReport weekReport, List<KeyResult> keyResults){
		
		Integer weekReportId = weekReport.getId();
		
		List<WeekReportRel> weekReportRels = new ArrayList<WeekReportRel>();
		if(keyResults!=null&&keyResults.size()>0){
			for(KeyResult keyResult:keyResults){
				//更新关键事件状态
				if(ConstantsUtil.OPREATE_TYPE_RELEASE_STATUS.equals(weekReport.getStatus())){
					keyResult.setStatus(KeyResultStatusEnum.STATUS2.getId());
				}else{
					keyResult.setStatus(KeyResultStatusEnum.STATUS1.getId());
				}
				

				WeekReportRel wrr = new WeekReportRel();
				wrr.setKeyResultId(keyResult.getId());
				wrr.setWeekReportId(weekReportId);
				weekReportRels.add(wrr);
			}
			
		}
		
		deleteWeekReportRelList(weekReportId);
		
		//更新关键事件状态已开始
		keyResultDao.updateKeyResultStatus(keyResults);
		
		weekReportDao.addWeekReportRel(weekReportRels);
		
	}
	
	/**
	 * 更新周报周报
	 * */
	private void updateWeekReport(WeekReport weekReport){
		Integer weekReportId = weekReport.getId();
		
		List<Integer> keyResultIds = getKeyResultIds(weekReportId,weekReport.getKeyResultList());
		
		weekReportDao.updateWeekReport(weekReport);
		//保存周报关键事件关系表
		saveWeekReportRelList(weekReport,weekReport.getKeyResultList());
		//更新周报附件信息
		updateAppendixList(weekReportId,weekReport.getAppendixList());
		
		//检查更新关键事件进行状态
		TaskScheduler.scheduleTaskAt(updateKeyResultStatusTask, MyDateUtils.addSeconds(new Date(), 2), keyResultIds,null, null);
		
	}
	
	/**
	 * 删除周报周报
	 * */
	private void  deleteWeekReport(Integer weekReportId){
		
		List<Integer> keyResultIds = getKeyResultIds(weekReportId,null);

		weekReportDao.deleteWeekReport(weekReportId);//删除周报
		deleteWeekReportRelList(weekReportId);//删除周报关系表
		deleteAppendixList(weekReportId);//删除周报附件表
		
		//检查更新关键事件进行状态
		TaskScheduler.scheduleTaskAt(updateKeyResultStatusTask, MyDateUtils.addSeconds(new Date(), 2), keyResultIds,null, null);
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
		if(appendixs!=null&&appendixs.size()>0){
			appendixMapper.updateAppendix(appendixs);
		}
		
	}
	
	/**
	 * 删除附件信息
	 * */
	private Integer deleteAppendixList(Integer weekReportId){

		List<Appendix> appendixs= appendixMapper.getAppendixByWeekReportId(weekReportId);
		
		
		return fileService.deleteList(appendixs);

	}
	
	@Override
	public WeekReport getWeekReportById(Integer id) {
		return weekReportDao.getWeekReportById(id);
	}
	
	
	/**
	 * 获取更新前周报关联结果id
	 * afterKeyResultIds 更新周报需要更新当前关键结果状态
	 * 
	 * */
	private List<Integer> getKeyResultIds(Integer weekReportId,List<KeyResult> afterKeyResultIds){
		List<Integer> keyResultIds = new ArrayList<Integer>();
		
		//查询周报更新前的关联关键事件信息
		WeekReportRel weekReportRel = new WeekReportRel();
		weekReportRel.setWeekReportId(weekReportId);
		List<WeekReportRel> weekReportRelList = weekReportDao.getWeekReportRelList(weekReportRel);
		if(weekReportRelList!=null){
			for(WeekReportRel wrr:weekReportRelList){
				keyResultIds.add(wrr.getKeyResultId());
			}
		}
		
		//检查要更新的周报关联结果
		if(afterKeyResultIds!=null&&afterKeyResultIds.size()>0){
			for(KeyResult keyResult:afterKeyResultIds){
				keyResultIds.add(keyResult.getId());
			}
		}
		
		return keyResultIds;
	}
}
