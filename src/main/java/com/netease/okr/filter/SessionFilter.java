package com.netease.okr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netease.okr.redis.RedisClient;
import com.netease.okr.util.CookieUtil;

public class SessionFilter implements Filter {
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  
            throws IOException, ServletException {  
        // 当session不为空的时候，要刷新cookie存活周期和redis中键的存活日期  
        String csessionId = CookieUtil.getJsessionId((HttpServletRequest) request);  
        if (csessionId != null && !"".equals(csessionId)) {  
        	
            RedisClient.expire(csessionId,CookieUtil.KEY_EXPIRE_TIME);
            
            CookieUtil.addCookie((HttpServletRequest) request, (HttpServletResponse) response,  
                    CookieUtil.KEY_EXPIRE_TIME);  
        }  
        chain.doFilter(request, response);  
    }  

	@Override
	public void destroy() {

	}

}