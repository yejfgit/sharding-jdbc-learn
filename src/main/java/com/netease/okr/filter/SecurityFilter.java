package com.netease.okr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.netease.okr.common.UserContext;
import com.netease.okr.model.entity.OptLog;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.OptLogService;
import com.netease.okr.util.IPUtil;
import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.UserContextUtil;

public class SecurityFilter implements Filter {
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			
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
			

		}
	}

	@Override
	public void destroy() {

	}

}