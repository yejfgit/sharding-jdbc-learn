package com.netease.okr.redis;

/**
 * purpose: redis key常量定义类
 * 
 * @author : hzyejinfu
 */
public interface RedisConstant {
	
	String HAS_NEW_WEEK_REPORT_KEY = "OKR_HAS_NEW_WEEK_REPORT_KEY_";//是否有的周报OKR_HAS_NEW_WEEK_REPORT_+{userId}
	
	Integer HAS_NEW_WEEK_REPORT_EXPIRE = 24 * 60 * 60;//最新周报缓存时间【24小时】
	
	String WEEK_REPORT_COUNT_KEY = "OKR_WEEK_REPORT_COUNT_KEY_";//周报数量OKR_WEEK_REPORT_COUNT_+{userId}
	
	Integer WEEK_REPORT_COUNT_EXPIRE = 30 * 60;//周报数量缓存时间【2小时】
	
	String OBJECTIVES_COUNT_KEY = "OKR_OBJECTIVES_COUNT_KEY_";//目标数量OKR_OBJECTIVES_COUNT_+{userId}
	
	Integer OBJECTIVES_COUNT_EXPIRE = 30 * 60;//目标数量缓存时间【2小时】

}
