package com.netease.okr.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.okr.common.JsonResponse;


@RestController
public class RedisController extends BaseController {
	
	private static final String APP_KEY = "ADDJYAG4845WED";
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/redis/clearRedis/{appKey}", method = RequestMethod.GET)
	public JsonResponse clearRedis(@PathVariable(value = "appKey") String appKey) {
		
		if(APP_KEY.equals(appKey)){
			
		}
		
		return null;
	}

	

}
