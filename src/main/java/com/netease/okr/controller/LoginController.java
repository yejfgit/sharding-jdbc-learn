package com.netease.okr.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.UserService;

@Controller
/*@RestController*/
public class LoginController extends BaseController {

	public final static String USER_SESSION_KEY = "session_user_key";
	public final static String USER_AREA_KEY = "user_area_key";
	public final static String USER_CHECKED_AREA_KEY = "user_checked_area_key";

	@Autowired
	protected UserService userService;
	
	@RequestMapping(value = "/login/index")
	public String loginSuccess(ModelMap map, Principal principal, HttpServletRequest request) {

		/*String name = principal.getName();*/
		String name = "";
	/*	User user = userService.getUserByUsername(name, true);
		request.getSession().setAttribute(USER_SESSION_KEY, user);*/
		//request.getSession().setAttribute(USER_AREA_KEY, user.getUserAreas());
		//request.getSession().setAttribute(USER_CHECKED_AREA_KEY, user.getUserAreas().get(0));

		return "/login";
	}

	@RequestMapping(value = "/login")
	public String login(ModelMap map, Integer error) {
		
		//TaskScheduler.scheduleTaskAt(syncOaDoEntrantJob, MyDateUtils.addSeconds(new Date(), 2), null, null,null);
		
		if (error != null) {
			if (error.intValue() == 1)
				map.addAttribute("errorMessage", "用户名或密码错误!");
			else if (error.intValue() == 3)
				map.addAttribute("errorMessage", "用户已从别处登录!");
		}
		return "/login2";
	}

	@RequestMapping(value = "/login/denied")
	public String loginerror(ModelMap map) {
		return "/common/403.html";
	}
/*
	@RequestMapping(value = "/logout")
	public String logout(ModelMap map) {
		return "/login";
	}*/

	@RequestMapping(value = "/login/login2")
	public String loginOK(ModelMap map) {
		return "/index";

	}
}
