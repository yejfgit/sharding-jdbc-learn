package com.netease.learn.common;
import com.alibaba.fastjson.JSON;
import com.netease.learn.util.ConstantsUtil;
import com.netease.learn.util.LoggerUtil;
public class JsonUtil {  
	
	public static JsonResponse toJsonObj(Object obj){
		JsonResponse res = new JsonResponse(); 
		try {
			res.setData(obj);
			res.setCode(ConstantsUtil.RESPONSE_SUCCESS_200);
			res.setMsg(ConstantsUtil.RESPONSE_MSG_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			res.setCode(ConstantsUtil.RESPONSE_FAILED_400);
			res.setMsg(ConstantsUtil.RESPONSE_MSG_FAILED+":【toJsonObj异常】");
			LoggerUtil.error("",e);
		}
		
		LoggerUtil.info(JSON.toJSONString(res));
		
		return res;
	}
	
	public static JsonResponse toJsonFail(String msg){
		JsonResponse res = new JsonResponse(); 
		res.setCode(ConstantsUtil.RESPONSE_FAILED_400);
		res.setMsg("【"+msg+"】");
		
		LoggerUtil.info(JSON.toJSONString(res));
		return res;
	}
	
	public static Object jsonToObj(String jsonObj,Object obj){
		
		try {
			return JSON.parseObject(jsonObj, obj.getClass());
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("jsonToObj异常",e);
			return null;
		}
	}
	

}  