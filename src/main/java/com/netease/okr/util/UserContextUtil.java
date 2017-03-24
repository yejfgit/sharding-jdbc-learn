package com.netease.okr.util;

import com.netease.okr.common.UserContext;

/**
 * @author yejf
 */
public class UserContextUtil {

	public static final String USER_CONTEXT_NAME = "userContext";

	private static ThreadLocal<Object> instance = new ThreadLocal<Object>();

	public static int initUserContext(UserContext userContext) {
		if (userContext == null) {
			return -1;
		}
		instance.set(userContext);
		return 0;
	}

	public static int destoryUserContext() {
		instance.set(new Object());
	
		return 0;
	}

	/*public static UserContext getUserContext() {
		UserContext userCtx = null;
		Object obj = instance.get();
		if (obj instanceof UserContext) {
			userCtx = (UserContext) obj;
		}
		
		return userCtx;
	}*/

}
