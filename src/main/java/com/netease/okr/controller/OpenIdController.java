package com.netease.okr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.expressme.openid.ShortName;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.FetchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.okr.common.JsonResponse;
import com.netease.okr.common.UserContext;
import com.netease.okr.model.entity.security.User;
import com.netease.okr.service.UserService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.OpenIdAction;
import com.netease.okr.util.OpenIdUtil;
import com.netease.okr.util.UserContextUtil;


/**
 * OpenId登录控制器.
 */
@Controller
public class OpenIdController {
	
	private static final String INDEX_PAGE_SUCCESS = "/public/app.html#/home";
	
	private static final String INDEX_PAGE_FAILED = "/openid.do";
	
	/** 用户相关接口.  */
	@Autowired
	private UserService userService;

	/**
	 * 登录页
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		try {
			HttpServletRequest hsrq = (HttpServletRequest) request;
			UserContext userContext = (UserContext) hsrq.getSession().getAttribute(UserContextUtil.USER_CONTEXT_NAME);
			
			if(userContext!=null&&userContext.getUser()!=null){
				return "redirect:" + INDEX_PAGE_SUCCESS;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.getRequestDispatcher(INDEX_PAGE_FAILED).forward(request, response);
			
		}
		
		request.getRequestDispatcher(INDEX_PAGE_FAILED).forward(request, response);
		return null;
	}

	/**
	 * 开发用登录方式
	 * @param passport
	 * @param session
	 * @return
	 */
	/*@RequestMapping(value = "/login/{passport}/{password}")
	public String login(@PathVariable(value = "passport") String passport,
			@PathVariable(value = "password") String password, HttpSession session) {
		LoggerUtil.info(passport + " passport login.");
		if (passport.startsWith("yixin.")) {
			passport = passport.substring(6);
		}
		passport = passport + "@corp.netease.com";
		User user = userService.getUserByEmail(passport);
		if (user == null) {
			user = userService.getUserByEmail(passport.replace("@corp.netease.com", "@yixin.im"));
		}
		if (user != null) {
			if (null != password && password.equals(user.getPassword())) {
				setUserInfo(user);
				session.setAttribute(Constants.USER, user);
				String redirectUrl = session.getAttribute(Constants.LASTURL) == null ? ""
						: session.getAttribute(Constants.LASTURL).toString();
				if (StringUtils.isBlank(redirectUrl)) {
					redirectUrl = "/app.html";
				}
				LoggerUtil.info("passport success.");
				return "redirect:" + redirectUrl;
			}
		}
		return "error/index";
	}*/

	/**
	 * openId登录
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/openid")
	public String OpenIdLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			// 两边存放的session，两边进行校验
			ConsumerManager manager = OpenIdUtil.getConsumerManager();

			// 所有地址
			ShortName shortName = new ShortName();
			String sRealm = shortName.lookupUrlByName("realm");
			String sReturnTo = shortName.lookupUrlByName("returnTo");
			// String openid = shortName.lookupUrlByName("netease");

			List<?> discoveries = manager.discover("https://login.netease.com/openid/");

			DiscoveryInformation discovered = manager.associate(discoveries);
			session.setAttribute("openid-disco", discovered);
			AuthRequest authReq = manager.authenticate(discovered, sReturnTo, sRealm);

			// 参数
			FetchRequest fetch = FetchRequest.createFetchRequest();
			fetch.addAttribute("email", "http://axschema.org/contact/email", true);

			authReq.addExtension(fetch);

			// 跳转openid的地址
			OpenIdAction Jopen = new OpenIdAction();
			String sUrl = Jopen.getOpenIdUri("netease", request);
			sUrl = sUrl.replaceAll("=\\{HMAC-SHA\\d{1,3}\\}\\{.*\\}\\{.*\\}&", "=&");

			response.sendRedirect(sUrl);
			return null;
		} catch (Exception e) {
			LoggerUtil.error("OpenId登录错误.", e);
			return null;
		}
	}

	/**
	 * 校验
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/receiveOpenId")
	public String receiveOpenId(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ConsumerManager manager = OpenIdUtil.getConsumerManager();
		boolean success = false;
		try {
			ParameterList responselist = new ParameterList(request.getParameterMap());

			DiscoveryInformation discovered = (DiscoveryInformation) session.getAttribute("openid-disco");

			StringBuffer receivingURL = request.getRequestURL();
			String queryString = request.getQueryString();
			if (queryString != null && queryString.length() > 0) {
				receivingURL.append("?").append(request.getQueryString());
			}
			AuthSuccess authResp = AuthSuccess.createAuthSuccess(responselist);
			LoggerUtil.info("Return from openid : " + authResp.getReturnTo());

			VerificationResult verification = manager.verify(receivingURL.toString(), responselist, discovered);

			Identifier verified = verification.getVerifiedId();
			if (verified != null) {
				AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();
				session.setAttribute("openid", authSuccess.getIdentity());
				session.setAttribute("openid-claimed", authSuccess.getClaimed());

				String openidStr = authSuccess.getIdentity();
				String pixPassport = openidStr.substring(authSuccess.getParameterValue("openid.op_endpoint").length());
				if (pixPassport.endsWith("/")) {
					pixPassport = pixPassport.substring(0, pixPassport.length() - 1);
				}
				String passport = pixPassport + "@corp.netease.com";
				// openID验证成功后，用户信息放入session
				
				HttpServletRequest hsrq = (HttpServletRequest) request;
				UserContext userContext = (UserContext) hsrq.getSession().getAttribute(UserContextUtil.USER_CONTEXT_NAME);
				
				User user = userService.getUserByEmail(passport);
				
				// 未登录的
				if (userContext == null) {
					userContext = new UserContext();
					userContext.setUser(user);
					// 把用户上下文放入会话中
					hsrq.getSession().setAttribute(UserContextUtil.USER_CONTEXT_NAME, userContext);
					
					return "redirect:" + INDEX_PAGE_SUCCESS;
				}
				
				return "redirect:" + INDEX_PAGE_SUCCESS;
				
			}
		} catch (Exception e) {
			LoggerUtil.error("登录失败", e);
		}
		if (!success) {
			OpenIdLogin(request, response, session);
		}
		return null;
	}

	/**
	 * 退出.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		request.getSession().invalidate();
		String redirectUrl = "/openid.do";
		return "redirect:" + redirectUrl;
	}

	/**
	 * 超时，需要重新登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/timeout")
	@ResponseBody
	public JsonResponse timeout() {
		LoggerUtil.info("超时或未登录");
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setCode(ConstantsUtil.RESPONSE_TIMEOUT);
		jsonResponse.setData("/openid.do");
		jsonResponse.setMsg("长时间未操作或未登录。");
		return jsonResponse;
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "index.do";
	}

}
