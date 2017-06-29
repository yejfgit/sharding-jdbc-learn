package com.netease.okr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.enums.DateModelEnum;
import com.netease.okr.model.entity.DateInfo;
import com.netease.okr.service.DateInfoService;
import com.netease.okr.util.JsonUtil;


@RestController
public class DateInfoController extends BaseController {

	@Autowired
	private DateInfoService dateInfoService;
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/date/getAllDate", method = RequestMethod.GET)
	public JsonResponse getAllDate() {
		
		List<DateInfo> dateInfos =dateInfoService.getAllDateInfo();
		
		return JsonUtil.toJsonObj(dateInfos);
	}
	
	
	
	@RequestMapping(value = "/date/getCurDate", method = RequestMethod.GET)
	public JsonResponse getCurDate() {
		
		DateInfo dateInfo =dateInfoService.getCurDateInfo();
		
		return JsonUtil.toJsonObj(dateInfo);
	}
	
	
	@RequestMapping(value = "/date/getClassDateByType", method = RequestMethod.GET)
	public JsonResponse getDateByModel(Integer type) {
		
		List<DateInfo> dateInfos =dateInfoService.getClassDateByModel(type==null?DateModelEnum.TYPE1.getId():type);
		
		return JsonUtil.toJsonObj(dateInfos);
	}
	

}
