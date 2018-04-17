package com.app.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	public boolean prehandle(HttpServletRequest request , HttpServletResponse response , Object handler)throws Exception{
		HttpSession session= request.getSession();
		if(session.getAttribute("userId") == null) {
			//로그인 페이지로 이동
			response.sendRedirect(request.getContextPath()+ "/member/login.do");
			//컨트롤러를 실행하지 않는다. ("요청 페이지로 이동하지 않다)
			return false;
		}else {
			return true;
		}
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
	
}
