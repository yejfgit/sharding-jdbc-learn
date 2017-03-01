package com.netease.okr.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class IPFilter implements Filter {

	private static Set<String> directUrl = new HashSet<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
//			HttpServletRequest req = (HttpServletRequest) request;
//			HttpSession session = req.getSession();
//			String ip = getRemoteIp(req);
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}
	
	/**
	 * 获取真实ip
	 * 
	 * @param request
	 * @return String
	 */
	public String getRemoteIp(HttpServletRequest request) {
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

}
