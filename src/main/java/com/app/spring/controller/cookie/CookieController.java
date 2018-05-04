package com.app.spring.controller.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@Controller
public class CookieController {
/*	
	//쿠키 생성
	@RequestMapping(value="/addCuki", method=RequestMethod.GET)
	public void createCookie(HttpServletResponse response, @ModelAttribute SiteVO vo){
		Cookie setCookie = new Cookie("name", vo.toString()); // 쿠키 생성(json으로 저장해야 될것 같다. )
		setCookie.setMaxAge(60*60*24); // 기간을 하루로 지정
		response.addCookie(setCookie);
		
	}
	
	
	
	//쿠키 가져오기
	@RequestMapping(value="/", method=RequestMethod.GET)
	public void getCookie(HttpServletRequest request){
		Cookie[] getCookie = request.getCookies();
			if(getCookie != null){
				for(int i=0; i < getCookie.length; i++){
				Cookie c = getCookie[i];
				String name = c.getName(); // 쿠키 이름 가져오기
				String value = c.getValue(); // 쿠키 값 가져오기
			}
		}
	}
	
	//특정 쿠키 제거
	@RequestMapping(value="/", method=RequestMethod.GET)
	public void removeCookie(HttpServletResponse response){
		Cookie kc = new Cookie("choiceCookieName", null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정
		kc.setMaxAge(0); // 유효시간을 0으로 설정
		response.addCookie(kc); // 응답 헤더에 추가해서 없어지도록 함
	}

	
	//모든 쿠키 제거
	@RequestMapping(value="/", method=RequestMethod.GET)
	public void removeAllCookie(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i=0; i< cookies.length; i++){
				cookies[i].setMaxAge(0); // 유효시간을 0으로 설정
				response.addCookie(cookies[i]); // 응답 헤더에 추가
			}
	
		}

	}
	*/
}
