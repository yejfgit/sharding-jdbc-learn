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
import com.netease.okr.model.entity.security.User;
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
			
			/*ucvo = new UserContext();
			User user = new User();
			user.setId(742);
			user.setName("叶金福");
			user.setuNo("H9299");
			user.setCorpMail("hzyejinfu@corp.netease.com");
			user.setDeptL1Id("D002");
			ucvo.setUser(user);
			hsrq.getSession().setAttribute(UserContextUtil.USER_CONTEXT_NAME, ucvo);*/
			
			if (ucvo == null&&toUrl.indexOf("receiveOpenId.do") == -1&&toUrl.indexOf("index.do") == -1
					&&toUrl.indexOf("openid.do") == -1&&toUrl.indexOf("logout.do") == -1) {
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