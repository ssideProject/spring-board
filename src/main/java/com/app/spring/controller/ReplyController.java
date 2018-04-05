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
import com.app.spring.service.board.ReplyPager;



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
	
	//댓글입력
	@RequestMapping("insert.do")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session, @RequestParam String secretReply) {
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		vo.setReplyer(id);
		vo.setUserName(name);
		vo.setSecretReply(secretReply);
		replyService.create(vo);
	}
	
	//댓글목록 (controller방식: view를 리턴한다.)
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam int bno, @RequestParam(defaultValue="1") int curPage,
							ModelAndView mav, HttpSession session) {
		//페이징 처리
		int count = replyService.count(bno);
		ReplyPager replyPager = new ReplyPager(count,curPage);
		int start = replyPager.getPageBegin();
		int end = replyPager.getPageEnd();
		List<ReplyVO> list = replyService.list(bno,start,end,session);
		
		//뷰이름지정
		mav.setViewName("board/replyList");
		//뷰에 전달할 데이터 지정
		mav.addObject("list", list);
		mav.addObject("replyPager", replyPager);
		return mav;
	}
	
	
	@RequestMapping("listJson.do")
	@ResponseBody // 리턴데이터를 Json으로 변환(생략가능)
	public List<ReplyVO> listJson(@RequestParam int bno){
		List<ReplyVO> list = replyService.list(bno);
		return list;
	}
	
}
