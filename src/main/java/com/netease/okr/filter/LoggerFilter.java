package com.netease.okr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import com.netease.okr.model.entity.security.User;
import com.netease.okr.util.IPUtil;
import com.netease.okr.util.UserContextUtil;


public class LoggerFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String userName = null;
		if (session != null) {
			User user = (User) UserContextUtil.getUserContext().getUser();
			if (user != null)
				userName = user.getName();
		}

		MDC.put("userName", userName != null ? userName : "noLogin");
		MDC.put("userIP",
				new StringBuffer().append("[").append(IPUtil.getRemoteIp(req))
						.append("]"));

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {

	}

}
