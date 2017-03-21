package com.netease.okr.util;  
  
import java.util.UUID;  
  
import javax.servlet.http.Cookie;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
public class CookieUtil {  
  
    /** 设置过期时间*/  
    public static Integer KEY_EXPIRE_TIME =  2 * 60 * 60; //秒 
    
    private static final String COOKIE_NAME = "OKRSESSIONID";
    
    private static final String COOKIE_PRE = "OKR";
  
    /** 
     * 获取JessionId 
     * @param request 
     * @return 
     */  
    public static String getJsessionId(HttpServletRequest request) {  
        Cookie[] cookies = request.getCookies();  
        // 从Cookie数据中遍历查找 并取CSESSIONID  
        if (null != cookies && cookies.length > 0) {  
            for (Cookie cookie : cookies) {  
                if (COOKIE_NAME.equals(cookie.getName())) {  
                    // 有 直接返回  
                    return cookie.getValue();  
                }  
            }  
        }  
        return null;  
    }  
  
    public static String addCookie(HttpServletRequest request, HttpServletResponse response, Integer KEY_EXPIRE_TIME) {  
        String csessionId = getJsessionId(request);  
        if (csessionId == null || "".equals(csessionId)) {  
            csessionId = COOKIE_PRE+UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();  
        }  
        Cookie cookie = new Cookie(COOKIE_NAME, csessionId);  
        cookie.setPath("/");  
        cookie.setMaxAge(KEY_EXPIRE_TIME);  
        response.addCookie(cookie);  
        return csessionId;  
    }  
} 