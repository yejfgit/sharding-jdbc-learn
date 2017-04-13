package com.netease.okr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.GroupObjectives;
import com.netease.okr.model.entity.GroupObjectivesMile;
import com.netease.okr.model.entity.GroupObjectivesRelList;
import com.netease.okr.model.entity.GroupObjectivesUser;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.GroupObjectivesMileService;
import com.netease.okr.service.GroupObjectivesService;
import com.netease.okr.service.GroupObjectivesUserService;
import com.netease.okr.service.GroupOkrService;
import com.netease.okr.util.JsonUtil;
import com.netease.okr.util.MyStringUtil;
import com.netease.okr.util.RedisUserContextUtil;

/**
 * @author yejf
 * */
@RestController
public class GroupOkrController extends BaseController {
	
	@Autowired
	private GroupObjectivesService groupObjectivesService;
	
	@Autowired
	private GroupObjectivesMileService groupObjectivesMileService;
	
	@Autowired
	private GroupObjectivesUserService groupObjectivesUserService;
	
	@Autowired
	private GroupOkrService groupOkrService;
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myGroupOkr/getMyGroupOkrList", method = RequestMethod.GET)
	public JsonResponse getMyGroupOkrList(Integer userId) {
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		List<GroupObjectives> myGroupOkrList = groupObjectivesService.getGroupObjectivesList(user.getDeptL1Id());
		
		return JsonUtil.toJsonObj(myGroupOkrList);
	}
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myGroupOkr/addGroupObjectives", method = RequestMethod.POST)
	public JsonResponse addGroupObjectives(@RequestBody GroupObjectives groupObjectives) {
		
		if(groupObjectives==null){
			return JsonUtil.toJsonFail("组织目标信息为空！");
		}
		
		if(groupObjectives!=null&&(groupObjectives.getUserList()==null||groupObjectives.getUserList().size()<1)){
			return JsonUtil.toJsonFail("组织目标负责人和参与人为空！");
		}
		
		groupObjectives = groupOkrService.addGroupObjectives(groupObjectives);
		
		return JsonUtil.toJsonObj(groupObjectives);
	}
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myGroupOkr/updateGroupObjectives", method = RequestMethod.POST)
	public JsonResponse updateGroupObjectives(@RequestBody GroupObjectives groupObjectives) {
		
		if(groupObjectives==null){
			return JsonUtil.toJsonFail("组织目标信息为空！");
		}
		
		if(groupObjectives!=null&&groupObjectives.getId()==null){
			return JsonUtil.toJsonFail("组织目标id为空！");
		}
		
		groupObjectives = groupOkrService.updateGroupObjectives(groupObjectives);
		
		return JsonUtil.toJsonObj(groupObjectives);
	}
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myGroupOkr/deleteGroupObjectives", method = RequestMethod.POST)
	public JsonResponse deleteGroupObjectives(@RequestBody GroupObjectives groupObjectives) {
		
		if(groupObjectives==null){
			return JsonUtil.toJsonFail("组织目标信息为空！");
		}
		
		if(groupObjectives!=null&&groupObjectives.getId()==null){
			return JsonUtil.toJsonFail("组织目标id为空！");
		}
		
		groupObjectives = groupOkrService.deleteGroupObjectives(groupObjectives);
		
		return JsonUtil.toJsonObj(groupObjectives);
	}
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myGroupOkr/addMile", method = RequestMethod.POST)
	public JsonResponse addMile(@RequestBody GroupObjectivesMile groupObjectivesMile) {
		
		if(groupObjectivesMile==null){
			return JsonUtil.toJsonFail("信息为空！");
		}
		
		if(groupObjectivesMile!=null&&(groupObjectivesMile.getMileDate()==null||MyStringUtil.isBlank(groupObjectivesMile.getMileDsc()))){
			return JsonUtil.toJsonFail("里程碑时间或内容为空！");
		}
		
		int c = groupObjectivesMileService.addGroupObjectivesMile(groupObjectivesMile);
		
		if(c>0){
			return JsonUtil.toJsonObj(groupObjectivesMile);
		}else{
			return JsonUtil.toJsonFail("删除失败");
		}
	}
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myGroupOkr/deleteMile", method = RequestMethod.POST)
	public JsonResponse deleteMile(@RequestBody GroupObjectivesMile groupObjectivesMile) {
		
		if(groupObjectivesMile==null){
			return JsonUtil.toJsonFail("信息为空！");
		}
		
		if(groupObjectivesMile!=null&&(groupObjectivesMile.getId()==null)){
			return JsonUtil.toJsonFail("ID为空！");
		}
		
		int c = groupObjectivesMileService.deleteGroupObjectivesMileById(groupObjectivesMile.getId());
		
		if(c>0){
			return JsonUtil.toJsonObj(groupObjectivesMile);
		}else{
			return JsonUtil.toJsonFail("删除失败");
		}
	}
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myGroupOkr/updateMile", method = RequestMethod.POST)
	public JsonResponse updateMile(@RequestBody GroupObjectivesMile groupObjectivesMile) {
		
		if(groupObjectivesMile==null){
			return JsonUtil.toJsonFail("信息为空！");
		}
		
		if(groupObjectivesMile!=null&&(groupObjectivesMile.getId()==null||groupObjectivesMile.getMileDate()==null||MyStringUtil.isBlank(groupObjectivesMile.getMileDsc()))){
			return JsonUtil.toJsonFail("里程碑ID、时间或内容为空！");
		}
		
		int c = groupObjectivesMileService.updateGroupObjectivesMile(groupObjectivesMile);
		if(c>0){
			return JsonUtil.toJsonObj(groupObjectivesMile);
		}else{
			return JsonUtil.toJsonFail("更新失败");
		}
		
	}
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myGroupOkr/getGroupObjectivesRel", method = RequestMethod.GET)
	public JsonResponse getGroupObjectivesRel(Integer groupObjectivesId) {
		
		if(groupObjectivesId==null){
			return JsonUtil.toJsonFail("groupObjectivesId为空！");
		}
		
		List<GroupObjectivesUser> groupObjectivesUserList = groupObjectivesUserService.getGroupObjectivesUsers(groupObjectivesId);
		
		return JsonUtil.toJsonObj(groupObjectivesUserList);
	}
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myGroupOkr/updateGroupObjectivesRel", method = RequestMethod.POST)
	public JsonResponse updateGroupObjectivesRel(@RequestBody GroupObjectivesRelList groupObjectivesRelList) {
		
		if(groupObjectivesRelList==null){
			return JsonUtil.toJsonFail("信息为空！");
		}
		
		if(groupObjectivesRelList!=null&&(groupObjectivesRelList.getGroupObjectivesId()==null||groupObjectivesRelList.getObjectivesIdList()==null)){
			return JsonUtil.toJsonFail("组织目标ID或关联目标为空！");
		}
		
		int c = groupObjectivesService.addGroupObjectivesRel(groupObjectivesRelList);
		if(c>0){
			return JsonUtil.toJsonObj(groupObjectivesService.getObjectivesList(groupObjectivesRelList.getGroupObjectivesId()));
		}else{
			return JsonUtil.toJsonFail("更新失败");
		}
		
		
	}
	
}
