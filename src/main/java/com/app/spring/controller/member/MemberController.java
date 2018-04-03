package com.app.spring.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		boolean result = memberService.loginCheck(vo, session);
		ModelAndView mav = new ModelAndView();
		
		if(result== true) {
			mav.setViewName("home");
			mav.addObject("msg", "success");
		} else {
			mav.setViewName("member/login");
			mav.addObject("msg", "failure");
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
	
	//회원등록 페이지.
		//jsp에서 <form name="form1" method="post" action="${path}/member/insert.do"> 를 사용해서 insert로 넘어간다.
		@RequestMapping("write.do")
		public String memberWrite() {
			return "member/write";
		}
		
		//회원등록 처리후 -> 회원목록으로 리다이렉트
		//ModelAttribute에서 폼에서 입력한 데이터가 저장된다.
		@RequestMapping("insert.do")
		//** 폼에서 입력한 데이터를 받아오는 법 3가지?
		//public String memberInsert(HttpServlet request){}
		//publit String memberInsert(String userId, String userPw, String userName, STring userEmail){}
		public String memberInsert(@ModelAttribute MemberVO vo) {
			//테이블엥 레코드 입력
			memberService.insertMember(vo);
			// * (/)의 차이가 있다.
			//    /member/list.do 루트 디렉토리 기준
			//    member/lsit.do 현재 디렉토리 기준
			//   member_list.jsp로 다이렉트
			return "redirect:/board/list.do";
		}	
		
		
		//회원 정보 상세조회!! 흠 드디어 해보게 되는건가.!!! 따라라라
		@RequestMapping("view.do")
		public String memberView(HttpSession session, Model model) {
			String id = (String) session.getAttribute("id");
			model.addAttribute("dto" , memberService.detailMember(id));
			return "member/view";
			
		}
		// 04. 회원 정보 수정 처리
	    @RequestMapping("update.do")
	    public String memberUpdate(@ModelAttribute MemberVO vo){
	        memberService.updateMember(vo);
	        return "redirect:/board/list.do";
	    }
	    
	    
	    
	    //회원의 삭제
	    // @RequestMapping: url mapping
	    // @RequestParam : get or post방식으로 전달된 변수값.
	    @RequestMapping("delete.do")
	    public String memberDelete(@RequestParam String id, @RequestParam String passwd, Model model) {
	    	//비밀번호 체크
	    	boolean result = memberService.checkPasswd(id, passwd); //viewMember로 대체가 가능하지 않을까?
	    	if(result) {
	    		memberService.deleteMember(id);
	    		return "redirect:/member/list.do";
	    	}else {
	    		model.addAttribute("message", "비밀번호가 틀리다.");
	    		model.addAttribute("dto", memberService.detailMember(id));
	    		return "member/view";
	    	}
	    }
	
}
