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
import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.OkrService;
import com.netease.okr.util.JsonUtil;
import com.netease.okr.util.UserContextUtil;


@RestController
public class OkrController extends BaseController {

	@Autowired
	private OkrService okrService;
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myOkr/getMyOkrList", method = RequestMethod.POST)
	public JsonResponse getMyOkrList() {
		
		User user = (User) UserContextUtil.getUserContext().getUser();
		
		List<Objectives> okrList = okrService.getMyOkrList(user.getId());
		
		return JsonUtil.toJsonObj(okrList);
	}
	
	
	
	/**
	 * 添加目标
	 * @param type='add' || update || delelte
	 * @return JsonResponse
	 */
	@ResponseBody
	@RequestMapping(value = "/myOkr/addObjectives", method = RequestMethod.POST)
	public JsonResponse addObjectives(@RequestParam("type") String type,@RequestBody Objectives objectives) {
		
		return okrService.addObjectives(type, objectives);
		
	}
	
	
	/**
	 * 添加关键事件
	 * @param type='add' || update || delelte
	 * @return JsonResponse
	 */
	@ResponseBody
	@RequestMapping(value = "/myOkr/addKeyResult", method = RequestMethod.POST)
	public JsonResponse addKeyResult(@RequestParam("type") String type,@RequestBody KeyResult keyResult) {
		
		return okrService.addKeyResult(type, keyResult);
		
	}

}
