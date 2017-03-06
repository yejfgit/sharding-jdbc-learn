package com.netease.okr.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ==================================================================
 * IPUtil.java created by yydx811 at 2014年2月28日 下午2:04:12 ip校验工具
 * 
 * @author yydx811
 * @version 1.0
 * @mail yydx811@jtang.cn,yydx811@163.com
 *       ==================================================================
 */
public class IPUtil {

	private static final Log log = LogFactory.getLog(IPUtil.class);

	/**
	 * ip地址正则表达式
	 */
	static final Pattern ipPattern = Pattern
			.compile("^(([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.)(([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.){2}([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))$");

	/**
	 * 获取真实ip
	 * 
	 * @param request
	 * @return String
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ip)
				|| StringUtils.equalsIgnoreCase("unknown", ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip)
				|| StringUtils.equalsIgnoreCase("unknown", ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip)
				|| StringUtils.equalsIgnoreCase("unknown", ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * ip白名单判断
	 * 
	 * @param ip
	 * @param whiteList
	 * @return boolean
	 */
	public static boolean canInvokeIP(String ip, String[] whiteList) {
		// 默认允许任意IP调用
		if (whiteList == null || whiteList.length == 0) {
			log.warn("WhiteList is empty[allow all IP invoke]");
			return true;
		}
		boolean can = false;
		for (String wip : whiteList) {
			// 正则匹配
			Pattern pattern = Pattern.compile(wip.trim());
			Matcher matcher = pattern.matcher(ip);
			if (matcher.find()) {
				can = true;
				break;
			}
		}
		return can;
	}

	/**
	 * 判断ip是否合法
	 * 
	 * @param ip
	 * @return boolean
	 */
	public static boolean isIP(String ip) {
		if (StringUtils.isBlank(ip)) {
			return false;
		}
		Matcher m = ipPattern.matcher(ip);
		return m.find();
	}

}
