package com.netease.okr.util;

/**
 * purpose: 常量定义类
 * 
 * @author : hzyejinfu
 */
public interface ConstantsUtil {
	
	/** 成功状态码. */
	String RESPONSE_SUCCESS = "200";

	/** 失败状态码. */
	String RESPONSE_FAILED = "400";

	/** 对被请求页面的访问被禁止. */
	String RESPONSE_CODE_403 = "403";

	/** 系统异常状态码. */
	String RESPONSE_CODE_500 = "500";

	/** session超时. */
	String RESPONSE_TIMEOUT = "TIMEOUT";
	
	String RESPONSE_MSG_SUCCESS = "ok";
	
	String RESPONSE_MSG_FAILED = "failed";
	
	Integer DEPT_LEVEL_L1 = 1;//部门等级
	Integer DEPT_LEVEL_L2 = 2;
	Integer DEPT_LEVEL_L3 = 3;
	
	String HR_DEPT_CODE = "D002";//人力资源部
	
	/** 文件上传临时目录. */
	public static final String FILE_TMP_DIR_PATH = "/data/upload/okr";
	
	Integer STATUS_YES = 1;//有效
	
	String OPREATE_TYPE_ADD = "add";//添加
	String OPREATE_TYPE_UPDATE = "update";//更新
	String OPREATE_TYPE_DEL = "delete";//删除
	String OPREATE_TYPE_RELEASE = "release";//暂存
	String OPREATE_TYPE_SAVE = "save";//保存
	Integer OPREATE_TYPE_RELEASE_STATUS = 0;//暂存状态
	Integer OPREATE_TYPE_SAVE_STATUS = 1;//保存状态
	
	String SKEY = "a0b891c2d563e4f7-----------";//薪资加密
	
	String IV_PARAMETER = "0123456789abcdef-----------";//薪资加密
	
	String OBJECTIVES_CODE_PRE = "O";
	
	String OBJECTIVES_NAME_PRE = "目标";
	
	String KRY_RESULT_CODE_PRE = "KR";
	
	
	


}
