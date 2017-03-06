package com.netease.okr.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.model.entity.Appendix;
import com.netease.okr.service.FileService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.MyStringUtil;



@RestController
@RequestMapping("/file")
public class FileController extends BaseController{

	@Autowired
	private FileService fileService;

	// 附件格式
	private static Set<String> typeAppendixList = new HashSet<String>();
	// 附件大小（100M）
	private static long appendixSize = 100 * 1024 * 1024; // 100M;

	static {
		// 附件
		typeAppendixList.add("jpg");
		typeAppendixList.add("jpeg");
		typeAppendixList.add("png");
		typeAppendixList.add("ppt");
		typeAppendixList.add("pptx");
		typeAppendixList.add("doc");
		typeAppendixList.add("docx");
		typeAppendixList.add("pdf");
		typeAppendixList.add("rar");
		typeAppendixList.add("zip");
	}

	/**
	 * 上传文件(小于100M)
	 * 
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upload")
	public JsonResponse uploadFile(String id, MultipartHttpServletRequest request) {
		JsonResponse res = new JsonResponse();
		
		Iterator<String> itrator = request.getFileNames();
		MultipartFile file = request.getFile(itrator.next());
		
		String fn = file.getOriginalFilename();
		
		if(fn.length()>30){
			res.setCode(ConstantsUtil.RESPONSE_FAILED_400);
			res.setMsg("附件名称不能超过30个字符");
			return res;
			//return res;
		}
		String[] fns = fn.split("\\.");
		String fnSuf = fns[fns.length - 1];
		LoggerUtil.uploadLogInfo("附件类型：" + fnSuf);
		if (typeAppendixList.contains(fnSuf)) {
			LoggerUtil.uploadLogInfo("附件大小：" + file.getSize());
			if (checkUploadType(file)) {
				try {
					if(MyStringUtil.isNotBlank(id)){
						fileService.delete(Integer.parseInt(id));
					}
					Appendix appendix = fileService.upload(file);
					if (appendix == null) {
						res.setCode(ConstantsUtil.RESPONSE_FAILED_400);
						res.setMsg("上传附件错误");
					} else {
						Appendix appTmp = new Appendix();
						appTmp.setId(appendix.getId());
						appTmp.setName(appendix.getName());
						appTmp.setUrl(appendix.getUrl());
						res.setData(appTmp);
						res.setMsg(ConstantsUtil.RESPONSE_MSG_SUCCESS);
					}
				} catch (Exception e) {
					LoggerUtil.uploadLogError("上传附件错误", e);
				}
			} else {
				res.setCode(ConstantsUtil.RESPONSE_FAILED_400);
				res.setMsg("上传附件大小超过限制");
			}
		} else {
			res.setCode(ConstantsUtil.RESPONSE_FAILED_400);
			res.setMsg("上传附件格式错误");
		}
		return res;
	}

	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public JsonResponse deleteFile(String id) {
		JsonResponse res = new JsonResponse();
		try {
			String msg = "";
			if(MyStringUtil.isNotBlank(id)){
				msg = fileService.delete(Integer.parseInt(id));
			}else{
				res.setCode(ConstantsUtil.RESPONSE_FAILED_400);
				res.setMsg("未得到附件id");
				return res;
			}
			
			if(MyStringUtil.isBlank(msg)){
				res.setCode(ConstantsUtil.RESPONSE_SUCCESS_200);
				res.setMsg(ConstantsUtil.RESPONSE_MSG_SUCCESS);
			}else{
				res.setCode(ConstantsUtil.RESPONSE_FAILED_400);
				res.setMsg(ConstantsUtil.RESPONSE_MSG_FAILED);
			}
		
		} catch (Exception e) {
			LoggerUtil.uploadLogError("删除附件错误", e);
		}
		
		
		return res;
	}
	
	
	/**
	 * 判断附件大小
	 * 
	 * @param type
	 * @param file
	 * @return
	 */
	private boolean checkUploadType(MultipartFile file) {
		if (file.getSize() <= appendixSize) {
			return true;
		}
		return false;
	}
}
