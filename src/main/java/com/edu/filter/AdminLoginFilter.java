package com.edu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.util.MD5Util;

public class AdminLoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest Request = (HttpServletRequest) request;
		HttpServletResponse Response = (HttpServletResponse) response;
		Cookie[] cookies = Request.getCookies();
		String token ="";
		for(Cookie cookie : cookies){
		    if(cookie.getName().equals("token"))
		    	token=cookie.getValue();
		}
		if(!"".equals(token)){
			String[] parts = token.split("\\&");
			String temp = MD5Util.convertMD5(parts[1]);
			if(parts[0].equals(temp)){
				/*Response.sendRedirect("/admin/main.jsp"); */
				chain.doFilter(request,response);
			}
			else{
				Response.sendRedirect("/forfun/jsp/login.jsp?error=2"); 
			}
		}else{
			Response.sendRedirect("/forfun/jsp/login.jsp?error=2"); 
		}
	}

	@Override
	public void destroy() {
		
	}

}
