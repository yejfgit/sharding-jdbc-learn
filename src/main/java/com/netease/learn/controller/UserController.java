package com.netease.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.learn.common.JsonResponse;
import com.netease.learn.common.JsonUtil;
import com.netease.learn.model.User;
import com.netease.learn.service.UserService;


@RestController
public class UserController extends BaseController {

	@Autowired
	protected UserService userService;
	
	
	@ResponseBody
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public JsonResponse getUser(@PathVariable("id") Integer id) {
		User user = userService.getUserById(id);

		return JsonUtil.toJsonObj(user);
	}
	

 
}
