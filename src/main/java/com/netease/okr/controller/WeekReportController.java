package com.netease.okr.controller;

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
import com.netease.okr.model.entity.WeekReportRel;
import com.netease.okr.service.WeekReportService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.JsonUtil;


@RestController
public class WeekReportController extends BaseController {

	@Autowired
	private WeekReportService weekReportService;
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/weekReport/getWeekReportListByKeyResultId", method = RequestMethod.POST)
	public JsonResponse getWeekReportListByKeyResultId(@RequestBody WeekReportRel weekReportRel) {
		
		if(weekReportRel==null||weekReportRel.getKeyResultId()==null){
			
			JsonResponse res = new JsonResponse(); 
			res.setCode(ConstantsUtil.RESPONSE_FAILED_400);
			res.setMsg(ConstantsUtil.RESPONSE_MSG_FAILED+"【keyResultId为空】");
			return res;
			
		}
		
		List<WeekReport> weekReportList = weekReportService.getWeekReportListByKeyResultId(weekReportRel.getKeyResultId());
		
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
