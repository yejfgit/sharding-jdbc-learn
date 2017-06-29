package com.netease.okr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.SummaryOther;
import com.netease.okr.service.SummaryOtherService;
import com.netease.okr.util.JsonUtil;


@RestController
public class SummaryOtherController extends BaseController {
	
	@Autowired
	protected SummaryOtherService summaryOtherService;

	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/summaryOther/getSummaryOtherList", method = RequestMethod.GET)
	public JsonResponse getSummaryOtherList(Integer summaryId) {
		
		
		List<SummaryOther> summaryOtherList = summaryOtherService.getSummaryOtherList(summaryId);
		
		return JsonUtil.toJsonObj(summaryOtherList);
	}
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/summaryOther/addSummaryOtherList", method = RequestMethod.POST)
	public JsonResponse addSummaryOtherList(@RequestBody List<SummaryOther> summaryOtherList) {
		
		if(summaryOtherList!=null&&summaryOtherList.size()>0){
			if(summaryOtherService.addSummaryOtherList(summaryOtherList)){
				return JsonUtil.toJsonObj(summaryOtherList);
			}else{
				return JsonUtil.toJsonFail("添加失败");
			}
		}else{
			return JsonUtil.toJsonFail("添加内容为空！");
		}
		
		
	}
	


}
