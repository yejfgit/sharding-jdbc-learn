package com.netease.okr.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.netease.okr.common.UserContext;
import com.netease.okr.redis.RedisClient;

/**
 * 使用ThreadLocal存储cookie key
 * @author yejf
 */
public class RedisUserContextUtil {

	private static final int TIME_OUT_SECONDS = CookieUtil.KEY_EXPIRE_TIME;//过期时间【秒】 

	private static ThreadLocal<Object> instance = new ThreadLocal<Object>();
	
	/**
	 * 获取cookie值
	 * */
	public static String getKey(HttpServletRequest request) {
		return CookieUtil.getJsessionId(request);
	}
	
	public static int initCookieKey(HttpServletRequest request) {
		String key = getKey(request);
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
	public static int initUserContext(UserContext userContext,ServletRequest request,ServletResponse response) {
		
		//初始化cookie
		CookieUtil.addCookie((HttpServletRequest) request, (HttpServletResponse) response,  CookieUtil.KEY_EXPIRE_TIME); 
		
		String key =(String)instance.get();
		
		//添加到redis
		String result = RedisClient.set(key, JSON.toJSONString(userContext));
		
		//设置redis过期时间
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
