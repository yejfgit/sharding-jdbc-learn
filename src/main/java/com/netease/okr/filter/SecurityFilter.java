package com.netease.okr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.netease.okr.common.UserContext;
import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.RedisUserContextUtil;

public class SecurityFilter implements Filter {
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/*
	 * 
	//普通登录过滤器
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			long startTime = System.currentTimeMillis();    //获取开始时间
			HttpServletRequest hsrq = (HttpServletRequest) request;
			UserContext ucvo = (UserContext) hsrq.getSession().getAttribute(UserContextUtil.USER_CONTEXT_NAME);
			
			String toUrl = hsrq.getRequestURI().replace(hsrq.getContextPath(), "")
				+ (hsrq.getQueryString() == null ? "" : "?" + hsrq.getQueryString()); 

			LoggerUtil.info("SecurityFilter url="+toUrl);
			
			if (ucvo == null&&toUrl.indexOf("receiveOpenId.do") == -1&&toUrl.indexOf("index.do") == -1
					&&toUrl.indexOf("openid.do") == -1&&toUrl.indexOf("logout.do") == -1&&toUrl.indexOf("/login/") == -1) {
				request.getRequestDispatcher("/timeout.do").forward(request, response);
				return;
			}
			
			UserContextUtil.initUserContext(ucvo);
			chain.doFilter(request, response);
			UserContextUtil.destoryUserContext();
			LoggerUtil.info("-----------------接口【"+hsrq.getRequestURI()+"执行时间： "+(System.currentTimeMillis()-startTime)+"ns】----------------"); 
			

		}
	}*/
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			long startTime = System.currentTimeMillis();    //获取开始时间
			
			HttpServletRequest hsrq = (HttpServletRequest) request;
			
			RedisUserContextUtil.initCookieKey(hsrq);
			UserContext ucvo = RedisUserContextUtil.getUserContext();
			
			
			String toUrl = hsrq.getRequestURI().replace(hsrq.getContextPath(), "")
				+ (hsrq.getQueryString() == null ? "" : "?" + hsrq.getQueryString()); 

			LoggerUtil.info("SecurityFilter url="+toUrl);
			
			if (ucvo == null&&toUrl.indexOf("receiveOpenId.do") == -1&&toUrl.indexOf("index.do") == -1
					&&toUrl.indexOf("openid.do") == -1&&toUrl.indexOf("logout.do") == -1&&toUrl.indexOf("/login/") == -1) {
				request.getRequestDispatcher("/timeout.do").forward(request, response);
				return;
			}
			
			try {
				chain.doFilter(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				LoggerUtil.error(toUrl+"-Exception",e);
			}
			
			RedisUserContextUtil.destoryCookieKey();
			
			LoggerUtil.info("-----------------接口【"+hsrq.getRequestURI()+"执行时间： "+(System.currentTimeMillis()-startTime)+"ns】----------------");
		}
	}

	@Override
	public void destroy() {

	}

}