package com.netease.okr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.Dept;
import com.netease.okr.service.DeptService;
import com.netease.okr.util.JsonUtil;


@RestController
public class DeptController extends BaseController {

	@Autowired
	protected DeptService deptService;
	
	/**
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dept/getDeptList", method = RequestMethod.GET)
	public JsonResponse getDeptInfo() throws Exception {
		
		List<Dept> depts =deptService.getAllDept();
		
		return JsonUtil.toJsonObj(depts);
	}
	

}
