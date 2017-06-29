package com.netease.okr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.Summary;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.OkrService;
import com.netease.okr.service.SummaryService;
import com.netease.okr.util.JsonUtil;
import com.netease.okr.util.RedisUserContextUtil;


@RestController
public class SummaryController extends BaseController {
	
	@Autowired
	protected SummaryService summaryService;
	
	@Autowired
	private OkrService okrService;
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/summary/getSummaryList", method = RequestMethod.GET)
	public JsonResponse getSummaryList() {
		
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		List<Summary> summaryList = summaryService.getSummaryList(user.getId());
		
		return JsonUtil.toJsonObj(summaryList);
	}
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/summary/getOkrSummaryList", method = RequestMethod.GET)
	public JsonResponse getOkrSummaryList(Integer userId) {
		
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		List<Objectives> okrList = new ArrayList<Objectives>();
		
		if(userId!=null){
			okrList = okrService.getOkrSummaryList(userId);
		}else{
			//默认查询自己okr
			okrList = okrService.getOkrSummaryList(user.getId());
			
		}
		
		return JsonUtil.toJsonObj(okrList);
	}

	

}
