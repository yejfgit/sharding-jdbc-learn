package com.netease.okr.util;

import com.alibaba.fastjson.JSON;
import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.security.User;

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
			res.setMsg(ConstantsUtil.RESPONSE_MSG_FAILED);
			LoggerUtil.error("",e);
		}
		
		LoggerUtil.info(JSON.toJSONString(res));
		
		return res;
	}
	
	public static JsonResponse toJsonFail(String msg){
		JsonResponse res = new JsonResponse(); 
		res.setCode(ConstantsUtil.RESPONSE_FAILED_400);
		res.setMsg(ConstantsUtil.RESPONSE_MSG_FAILED+":"+msg);
		
		LoggerUtil.info(JSON.toJSONString(res));
		return res;
	}
	
	public static Object jsonToObj(String jsonObj,Object obj){
		
		try {
			return JSON.parseObject(jsonObj, obj.getClass());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
    public static void main(String[] args) {  
        // 构建用户geust  
        User guestUser = new User();  
        guestUser.setName("guest");  
        guestUser.setId(1);  
        // 构建用户root  
        User rootUser = new User();  
        rootUser.setName("root");  
        guestUser.setId(2);
        
        
        String jsonString2 = JSON.toJSONString(guestUser); 
        
        System.out.println("jsonString2:" + jsonString2);
      /*  // 构建用户组对象  
        UserGroup group = new UserGroup();  
        group.setName("admin");  
        group.getUsers().add(guestUser);  
        group.getUsers().add(rootUser);  
        // 用户组对象转JSON串  
        String jsonString = JSON.toJSONString(group);  
        System.out.println("jsonString:" + jsonString);  
        // JSON串转用户组对象  
        UserGroup group2 = JSON.parseObject(jsonString, UserGroup.class);  
        System.out.println("group2:" + group2);  
  
        // 构建用户对象数组  
        User[] users = new User[2];  
        users[0] = guestUser;  
        users[1] = rootUser;  
        // 用户对象数组转JSON串  
        String jsonString2 = JSON.toJSONString(users);  
        System.out.println("jsonString2:" + jsonString2);  
        // JSON串转用户对象列表  
        List<User> users2 = JSON.parseArray(jsonString2, User.class);  
        System.out.println("users2:" + users2);  */
    }  
}  
