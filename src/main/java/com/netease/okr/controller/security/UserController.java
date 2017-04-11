package com.netease.okr.controller.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.okr.Lucence.UserIndexServer;
import com.netease.okr.common.JsonResponse;
import com.netease.okr.common.PageBean;
import com.netease.okr.common.PageJsonResponse;
import com.netease.okr.controller.BaseController;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.model.query.UserQuery;
import com.netease.okr.service.UserService;
import com.netease.okr.util.JsonUtil;
import com.netease.okr.util.MyStringUtil;
import com.netease.okr.util.RedisUserContextUtil;


@RestController
public class UserController extends BaseController {

	@Autowired
	protected UserService userService;
	
	/*@Autowired
	protected UserIndexServer userIndexServer;*/
	
	
	/**
	 * @param curUserId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/users/{uNo}", method = RequestMethod.GET)
	public JsonResponse getUser(@PathVariable("uNo") String uNo) {
		User user = userService.getUserByUNo(uNo);

		return JsonUtil.toJsonObj(user);
	}
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/getUserInfo", method = RequestMethod.GET)
	public JsonResponse getUserInfo(Integer userId) {
		
		User user = (User) RedisUserContextUtil.getUserContext().getUser();
		
		//查询其他用户信息
		if(userId!=null){
			user = userService.getUserById(userId);
		}

		return JsonUtil.toJsonObj(user);
	}
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/userList/{deptId}", method = RequestMethod.GET)
	public JsonResponse getUserList(@PathVariable("deptId") String deptId) {
		
		List<User> users = userService.getUserListOfDept(deptId);
		
		return JsonUtil.toJsonObj(users);
	}
	
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/queryUserList", method = RequestMethod.GET)
	public JsonResponse queryUserList(UserQuery user,PageBean<User> page) {
		User curUser = (User) RedisUserContextUtil.getUserContext().getUser();
		
		//默认查询本一级部门下人员
		if(user==null||MyStringUtil.isBlank(user.getDeptId())){
			user.setDeptId(curUser.getDeptL1Id());
		}
		
		PageJsonResponse<User> users = userService.getUsersOkrPage(user,page);
		
		return JsonUtil.toJsonObj(users);
	}
	
	/**
	 * @param 
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/user/searchUserList", method = RequestMethod.GET)
	public JsonResponse searchUser(UserQuery user,PageBean<User> page) {
		
		PageJsonResponse<User> users = userIndexServer.searchUser(user, page);
		
		return JsonUtil.toJsonObj(users);
	}*/
		
 
}
