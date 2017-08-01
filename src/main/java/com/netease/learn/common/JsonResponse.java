package com.netease.learn.common;

public class JsonResponse {
	private String code;
	private Object data;
	private String msg;
	
	public JsonResponse() {
		this.code = "200";
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
