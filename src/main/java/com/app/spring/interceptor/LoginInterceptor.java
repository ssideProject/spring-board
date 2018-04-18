package com.app.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		HttpSession session= request.getSession();
		
		if(session.getAttribute("id") == null) {
			//Ajax 콜인지 아닌지를 판단
			if(isAjaxRequest(request)) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
				// 여기부분 어렵네.. 흠.. 로그인을 확인하고, 아니면 다른 알림창을 띄우고 다른곳으로 리다이렉션을 해줘야하는데 흠.. 
			}else {	
				//로그인 페이지로 이동
				response.sendRedirect(request.getContextPath()+ "/member/login.do");
				//컨트롤러를 실행하지 않는다. ("요청 페이지로 이동하지 않다)
				return false;
			}
			
		}else {
			return true;
		}
		
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
	
	private boolean isAjaxRequest(HttpServletRequest req) {
		String  ajaxHeader = "AJAX";
		return req.getHeader(ajaxHeader) != null && req.getHeader(ajaxHeader).equals(Boolean.TRUE.toString());
	}
	
}
