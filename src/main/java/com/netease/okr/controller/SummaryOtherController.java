package com.netease.okr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
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
	@RequestMapping(value = "/summaryOther/delSummaryOther", method = RequestMethod.POST)
	public JsonResponse delSummaryOther(@RequestBody SummaryOther summaryOther) {
		
		if(summaryOther!=null&&summaryOther.getId()!=null){
			if(summaryOtherService.delSummaryOtherById(summaryOther.getId())){
				return JsonUtil.toJsonObj(summaryOther);
			}else{
				return JsonUtil.toJsonFail("删除失败！"+JSON.toJSONString(summaryOther));
			}
		}else{
			return JsonUtil.toJsonFail("信息错误!"+JSON.toJSONString(summaryOther));
		}
		
	}
	

}
