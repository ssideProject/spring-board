package com.app.spring.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.spring.model.dto.member.MemberVO;
import com.app.spring.service.member.MemberService;

@Controller // 현재 클래스를 스프링에서 관리하는 컨트롤러 bean으로 생성
@RequestMapping("/member/*")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService memberService;
	
	//01 로그인 화면
	@RequestMapping("login.do")
	public String login() {
		return "member/login";
	}
	
	//02 로그인처리
	@RequestMapping("loginCheck.do")
	public ModelAndView login(@ModelAttribute MemberVO vo, HttpSession session) {
		boolean result = memberService.loginCheck(vo,session);
		ModelAndView mav = new ModelAndView();
		
		if(result== true) {
			mav.setViewName("home");
			mav.addObject("msg", "success");
		} else {
			mav.setViewName("member/login");
			mav.addObject("msg", "failire");
		}
		return mav;
	}
	
	//03 로그아웃
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session) {
		memberService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		mav.addObject("msg", "logout");
		return mav;
	}
	
}
