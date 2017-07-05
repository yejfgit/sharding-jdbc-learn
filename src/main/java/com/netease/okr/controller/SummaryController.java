package com.netease.okr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.Summary;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.OkrService;
import com.netease.okr.service.SummaryService;
import com.netease.okr.util.JsonUtil;
import com.netease.okr.util.LoggerUtil;
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
	public JsonResponse getSummaryList(Integer userId) {
		
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		List<Summary> summaryList = new ArrayList<Summary>();;
		

		if(userId!=null){
			summaryList = summaryService.getSummaryList(userId);
		}else{
			summaryList = summaryService.getSummaryList(user.getId());
		}
		
		return JsonUtil.toJsonObj(summaryList);
	}
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/summary/getSummaryById", method = RequestMethod.GET)
	public JsonResponse getSummaryById(Integer id) {
		
		
		if(id==null){
			return JsonUtil.toJsonFail("查询ID为空");
		}
		
		Summary summary = summaryService.getSummaryById(id);
		
		return JsonUtil.toJsonObj(summary);
	}
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/summary/ifHasCreateSummary", method = RequestMethod.POST)
	public JsonResponse ifHasCreateSummary(@RequestBody Summary summary) {
		
		
		if(summary!=null&&summary.getDateTabId()!=null){
			Integer c = summaryService.getSummaryCountOfDate(summary.getDateTabId());

			return JsonUtil.toJsonObj(c);
		}else{
			return JsonUtil.toJsonFail("查询dateTabId为空!");
		}
		
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
	
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/summary/addSummary", method = RequestMethod.POST)
	public JsonResponse addSummary(@RequestBody Summary summary) {
		
		LoggerUtil.info("addSummary start:"+JSON.toJSONString(summary));
		try {
			if(summary!=null&&summary.getDateTabId()!=null){
				if(summaryService.addSummary(summary)){
					return JsonUtil.toJsonObj(summary);
				}else{
					return JsonUtil.toJsonFail("添加失败");
				}
			}else{
				return JsonUtil.toJsonFail("信息填写错误!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("addSummary exception", e);
			return JsonUtil.toJsonFail("添加数据异常!");
		}
		
	}
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/summary/updateSummary", method = RequestMethod.POST)
	public JsonResponse updateSummary(@RequestBody Summary summary) {
		
		LoggerUtil.info("updateSummary start:"+JSON.toJSONString(summary));
		
		try {
			if(summary!=null&&summary.getId()!=null&&summary.getDateTabId()!=null){
				if(summaryService.updateSummary(summary)){
					return JsonUtil.toJsonObj(summary);
				}else{
					return JsonUtil.toJsonFail("更新失败");
				}
			}else{
				return JsonUtil.toJsonFail("信息填写错误!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("updateSummary exception", e);
			return JsonUtil.toJsonFail("添加数据异常!");
		}
	}
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/summary/delSummary", method = RequestMethod.POST)
	public JsonResponse delSummary(@RequestBody Summary summary) {
		
		LoggerUtil.info("delSummary start:"+JSON.toJSONString(summary));
		
		try {
			if(summary!=null&&summary.getId()!=null){
				if(summaryService.delSummary(summary.getId())){
					return JsonUtil.toJsonObj(summary);
				}else{
					return JsonUtil.toJsonFail("更新失败");
				}
			}else{
				return JsonUtil.toJsonFail("信息填写错误!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("delSummary exception", e);
			return JsonUtil.toJsonFail("添加数据异常!");
		}
	}
	

	

}
