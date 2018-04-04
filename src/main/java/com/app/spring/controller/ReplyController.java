package com.app.spring.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.spring.model.dto.ReplyVO;
import com.app.spring.service.ReplyService;



//REST : Representational State Transfer
//하나의 URI가 하나의 고유한 리소스를 대쵸하도록 설계괸 개념

//Http://localhost/spring/list?bno=1   (url+파라미터)
//http://localhost/spring/list/1 (url)
//RESTController는 스프링 4.0부터 지원
//controller, @ restcontroller 차이점은 매서드가 종료되면 화면 전환의 유무


@RestController
@RequestMapping("/reply/*")
public class ReplyController {
	
	
	@Inject
	ReplyService replyService;
	
	@RequestMapping("insert.do")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session) {
		String userId = (String) session.getAttribute("id");
		vo.setReplyer(userId);
		replyService.create(vo);
	}
	
	
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam int bno, ModelAndView mav) {
		List<ReplyVO> list = replyService.list(bno);
		//뷰이름지정
		mav.setViewName("board/replyList");
		//뷰에 전달할 데이터 지정
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("listJson.do")
	@ResponseBody // 리턴데이터를 Json으로 변환(생략가능)
	public List<ReplyVO> listJson(@RequestParam int bno){
		List<ReplyVO> list = replyService.list(bno);
		return list;
	}
	
}
