package com.netease.okr.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.WeekReport;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.WeekReportQuery;
import com.netease.okr.service.WeekReportService;
import com.netease.okr.util.JsonUtil;
import com.netease.okr.util.MyDateUtils;
import com.netease.okr.util.MyStringUtil;
import com.netease.okr.util.UserContextUtil;


@RestController
public class WeekReportController extends BaseController {

	@Autowired
	private WeekReportService weekReportService;
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/weekReport/getWeekReportListByKeyResultId", method = RequestMethod.GET)
	public JsonResponse getWeekReportListByKeyResultId(@RequestParam("keyResultId") Integer keyResultId) {
		
		if(keyResultId==null){
			return JsonUtil.toJsonFail("【keyResultId为空】");
		}
		
		List<WeekReport> weekReportList = weekReportService.getWeekReportListByKeyResultId(keyResultId);
		
		return JsonUtil.toJsonObj(weekReportList);
	}
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/weekReport/getWeekReportList", method = RequestMethod.GET)
	public JsonResponse getWeekReportList(WeekReportQuery weekReportQuery) {
		User user = (User) UserContextUtil.getUserContext().getUser();
		
		if(weekReportQuery==null){
			weekReportQuery = new WeekReportQuery();
			
		}
		//默认查询自己周报
		if(weekReportQuery.getUserId()==null){
			weekReportQuery.setUserId(user.getId());
		}
		
		if(MyStringUtil.isBlank(weekReportQuery.getYear())){
			weekReportQuery.setYear(MyDateUtils.formatYDate(new Date(),""));
		}
		
		List<WeekReport> weekReportList = weekReportService.getWeekReportList(weekReportQuery);
		
		return JsonUtil.toJsonObj(weekReportList);
	}
	
	
	/**
	 * 添加周报
	 * @param  type="release" || “save”
	 * @return JsonResponse
	 */
	@ResponseBody
	@RequestMapping(value = "/weekReport/addWeekReport", method = RequestMethod.POST)
	public JsonResponse addKeyResult(@RequestParam("type") String type,@RequestBody List<WeekReport> weekReport) {
		
		
		return weekReportService.addWeekReports(type, weekReport);
		
	}
	
	
	/**
	 * 更新删除周报
	 * @param  type="release" || “save”
	 * @return JsonResponse
	 */
	@ResponseBody
	@RequestMapping(value = "/weekReport/updateWeekReport", method = RequestMethod.POST)
	public JsonResponse addKeyResult(@RequestParam("type") String type,@RequestBody WeekReport weekReport) {
		
		
		return weekReportService.updateWeekReports(type, weekReport);
		
	}
	

}
