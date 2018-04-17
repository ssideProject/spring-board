package com.app.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		System.out.println("pre handle.....");
		return true;
	}
	
	public void postHandle(HttpServletRequest request , HttpServletResponse response, Object handler, ModelAndView mav) throws Exception{
		System.out.println("post handle.....");
	}
}
