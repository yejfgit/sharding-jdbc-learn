package com.netease.okr.aop;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.netease.okr.common.UserContext;
import com.netease.okr.model.entity.OptLog;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.OptLogService;
import com.netease.okr.util.IPUtil;
import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.UserContextUtil;

/**
 * @author yejf
 */
public class OptLogAop {

	@Autowired
	private OptLogService optLogService;
	
	// 接口类型
	private static Set<String> urlTypeList = new HashSet<String>();

	static {
		// 附件
		urlTypeList.add("/myOkr/addObjectives.do");
		urlTypeList.add("/myOkr/addKeyResult.do");
		urlTypeList.add("/receiveOpenId.do");
		urlTypeList.add("/file/upload.do");
		urlTypeList.add("/weekReport/addWeekReport.do");
		urlTypeList.add("/weekReport/updateWeekReport.do");
	}

	public void before() {
	}
	
	public void after() {
		try {

			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();

			UserContext uc = (UserContext) request.getSession().getAttribute(UserContextUtil.USER_CONTEXT_NAME);
			if (request != null && uc != null && uc.getUser() != null
					&& urlTypeList.contains(request.getRequestURI())) {
				User user = (User) uc.getUser();
				OptLog optLog = new OptLog();
				optLog.setUserId(user.getId());
				optLog.setUrl(request.getRequestURI());
				optLog.setIp(IPUtil.getRemoteIp(request));

				optLogService.addOptLog(optLog);
			}
		} catch (Exception e) {
			// TODO: handle exception
			LoggerUtil.error("记录操作日志exception", e);
		}
	}
}
