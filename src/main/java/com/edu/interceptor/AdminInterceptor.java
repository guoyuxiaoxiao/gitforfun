package com.edu.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edu.util.MD5Util;

public class AdminInterceptor extends HandlerInterceptorAdapter{


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return false;
		/*Cookie[] cookies = request.getCookies();
		String token ="";
		for(Cookie cookie : cookies){
		    if(cookie.getName().equals("token"))
		    	token=cookie.getValue();
		}
		if(!"".equals(token)){
			String[] parts = token.split("\\&");
			String temp = MD5Util.convertMD5(parts[1]);
			if(parts[0].equals(temp)){
				return true;
			}
			else{
				response.sendRedirect("/forfun/jsp/login.jsp?error=2");
				return false;
			}
		}else{
			response.sendRedirect("/forfun/jsp/login.jsp?error=2"); 
			return false;
		}*/
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		response.sendRedirect("/forfun/jsp/login.jsp?error=2");
	}
	
}
