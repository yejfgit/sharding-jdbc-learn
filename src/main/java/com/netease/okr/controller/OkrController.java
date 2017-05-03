package com.netease.okr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.KeyResult;
import com.netease.okr.model.entity.Objectives;
import com.netease.okr.model.entity.OkrNum;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.ObjectivesService;
import com.netease.okr.service.OkrService;
import com.netease.okr.util.JsonUtil;
import com.netease.okr.util.RedisUserContextUtil;


@RestController
public class OkrController extends BaseController {

	@Autowired
	private OkrService okrService;
	
	@Autowired
	private ObjectivesService objectivesService;
	
	
	/**
	 * 查询所有状态关键事件
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myOkr/getMyOkrList", method = RequestMethod.GET)
	public JsonResponse getMyOkrList(Integer userId) {
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		List<Objectives> okrList = new ArrayList<Objectives>();
		
		if(userId!=null){
			okrList = okrService.getMyOkrList(userId);
		}else{
			//默认查询自己okr
			okrList = okrService.getMyOkrList(user.getId());
			
		}
		
		return JsonUtil.toJsonObj(okrList);
	}
	
	/**
	 * 查询不包含已终止和已完成的目标和关键事件
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myOkr/getMyNormalOkrList", method = RequestMethod.GET)
	public JsonResponse getMyNormalOkrList(Integer userId) {
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		List<Objectives> okrList = new ArrayList<Objectives>();
		
		if(userId!=null){
			okrList = okrService.getMyNormalOkrList(userId);
		}else{
			//默认查询自己okr
			okrList = okrService.getMyNormalOkrList(user.getId());
			
		}
		
		return JsonUtil.toJsonObj(okrList);
	}
	
	/**
	 * 根据目标id查询事件和周报
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myOkr/getOkrByObjId", method = RequestMethod.GET)
	public JsonResponse getOkrByObjId(Integer objectivesId) {
		Objectives Objectives = objectivesService.getObjectivesById(objectivesId);
		return JsonUtil.toJsonObj(Objectives);
	}
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myOkr/getMyOkrNum", method = RequestMethod.GET)
	public JsonResponse getMyOkrNum(Integer userId) {
		
		OkrNum okrNum = new OkrNum();
		
		if(userId!=null){
			okrNum = okrService.getMyOkrNum(userId);
		}else{
			return JsonUtil.toJsonFail("userId为空");
		}
		
		
		return JsonUtil.toJsonObj(okrNum);
	}
	
	/**
	 * 添加目标
	 * @param type='add' || update || delelte
	 * @return JsonResponse
	 */
	@ResponseBody
	@RequestMapping(value = "/myOkr/addObjectives", method = RequestMethod.POST)
	public JsonResponse addObjectives(@RequestBody Objectives objectives) {
		
		return okrService.addObjectives(objectives);
		
	}
	
	
	/**
	 * 添加关键事件
	 * @param type='add' || update || delelte
	 * @return JsonResponse
	 */
	@ResponseBody
	@RequestMapping(value = "/myOkr/addKeyResult", method = RequestMethod.POST)
	public JsonResponse addKeyResult(@RequestBody KeyResult keyResult) {
		
		return okrService.addKeyResult(keyResult);
		
	}

}
