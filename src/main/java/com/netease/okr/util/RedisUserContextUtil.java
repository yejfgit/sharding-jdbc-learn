package com.netease.okr.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.netease.okr.common.UserContext;
import com.netease.okr.redis.RedisClient;

/**
 * 使用ThreadLocal存储cookie key
 * @author yejf
 */
public class RedisUserContextUtil {

	private static final int TIME_OUT_SECONDS = 2*60*60;//过期时间【秒】 

	private static final String COOKIE_NAME = "_ntes_nnid";
	
	private static final String COOKIE_VALUE = "OKR";

	private static ThreadLocal<Object> instance = new ThreadLocal<Object>();
	
	/**
	 * 获取cookie值
	 * */
	public static String getKey(HttpServletRequest request) {
		
		if(request!=null){
			Cookie[] cookies = request.getCookies();
			LoggerUtil.info(request.getSession().getId());
			if(cookies!=null){
				for(int i=0;i<cookies.length;i++){
					Cookie cookie= cookies[i];
					
					LoggerUtil.info("cookieList【"+cookie.getName()+"="+cookie.getValue()+"】");
					
					if(COOKIE_NAME.equals(cookie.getName())){
						LoggerUtil.info("cookieUsed【"+cookie.getName()+"="+cookie.getValue()+"】");
						return COOKIE_VALUE+cookie.getValue();
					}
				}
			}
			
		}
		
		return null;
	}
	
	public static int initCookieKey(HttpServletRequest request) {
		String key = getKey(request);
		LoggerUtil.info("initCookieKey【"+key+"】");
		instance.set(key);
		
		return 0;
	}
	
	public static int destoryCookieKey() {
		
		instance.remove();
		
		return 0;
	}
	
	
	/**
	 * 初始化失败返回 0 成功返回1
	 * @author yejf
	 * */
	public static int initUserContext(UserContext userContext) {
		
		String key =(String)instance.get();
		LoggerUtil.info("initUserContext【"+key+"】");
		String result = RedisClient.set(key, JSON.toJSONString(userContext));
		RedisClient.expire(key,TIME_OUT_SECONDS);
		
		if("OK".equals(result)){
			return 1;
		}
		
		return 0;
	}
	
	
	public static int delUserContext() {
		
		String key =(String)instance.get();
		
		RedisClient.del(key);
		return 1;

	}

	public static UserContext getUserContext() {
		
		String key =(String)instance.get();
		
		if(MyStringUtil.isBlank(key)){
			return null;
		}
		
		String user = RedisClient.get(key);
		
		return JSON.toJavaObject(JSON.parseObject(user), UserContext.class);
		
	}

}
