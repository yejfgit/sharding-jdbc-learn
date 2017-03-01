package com.netease.okr.util.component;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.netease.okr.service.UserService;

public class SpringSecurityUtils {

	@Resource(name = "userServiceImpl")
	protected UserService userService;

	public SpringSecurityUtils() {
		super();
	}

	public SpringSecurityUtils(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 取得当前用户的登录名,如果当前用户未登录则返回null.
	 */
	public static String getCurrentUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null)
			return null;
		return auth.getName();
	}

	/**
	 * 取得当前用户信息,如果当前用户未登录则返回null.
	 *//*
	public User getCurrentUserDetail() {
		String userName = getCurrentUserName();
		return userService.getUserByUsername(userName, false);
	}*/

}
