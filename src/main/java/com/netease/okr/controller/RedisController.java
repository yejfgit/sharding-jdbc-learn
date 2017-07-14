package com.netease.okr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.service.GroupOkrService;
import com.netease.okr.service.WeekReportService;
import com.netease.okr.util.JsonUtil;


@RestController
public class RedisController extends BaseController {
	
	private static final String APP_KEY = "ADDJYAG4845WED";
	
	@Autowired
	private GroupOkrService groupOkrService;
	
	@Autowired
	private WeekReportService weekReportService;
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/redis/clearRedis/{appKey}", method = RequestMethod.GET)
	public JsonResponse clearRedis(@PathVariable(value = "appKey") String appKey) {
		
		if(APP_KEY.equals(appKey)){
			//更新缓存信息
			groupOkrService.updateGroupObjectivesListRedis();
			weekReportService.updateRedis();
		}
		
		return JsonUtil.toJsonObj("ok");
	}
	

}
