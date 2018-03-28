package com.app.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.spring.model.dto.BoardVO;
import com.app.spring.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired //@Inject도 가능하다.
	BoardService boardService;
	
	
	//게시글 목록
	@RequestMapping("list.do")
	public ModelAndView list() throws Exception{ // 모델뷰를 안써서 list()의 매개변수가 없다!
		List<BoardVO> list = boardService.listAll();
		ModelAndView mav = new ModelAndView(); //모델엔뷰가 왜 쓰이는걸까? 생각해보면, 메소드에서 인자값을 선언안해도된다.
		mav.setViewName("board/list"); //여기서 viewName을 셋팅하고 
		mav.addObject("list", list); // 여기서 오브젝트를 넘겨주고 모델뷰 객체 자체를 넘겨주면 되는것이다.
		return mav; //이전에는 Model이나 HttpsRequest,Respone 등을 선언해주고 뷰쪽으로 넘겨주었는데 간소화 시킨것.
		// list.jsp로 List가 같이 전달이 된다.
	}
	
	
	//게시글 작성 화면
	@RequestMapping(value="write.do", method=RequestMethod.GET) //@RequestMapping("board/write.do")
	public String write() {
		return "board/write";
	}
	
	
	//게시글 작성 처리
	@RequestMapping(value="insert.do", method = RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo) throws Exception{
		boardService.create(vo);
		return "redirect:list.do"; // "redirect:/list.do"와다른것인가??
	}
	
	//게시글 살세내용 조회+ 조회수가 올라간다.
	//@RequestParam : get/post 방식으로 전달된 변수 1개
	//HttpSession 세션 객체
	@RequestMapping(value="view.do", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno, HttpSession session) throws Exception{
		boardService.increaseViewcnt(bno, session); //조회수를 증가해주는것인데 왜 세션이 같이 넘어가는거지?
		ModelAndView mav = new ModelAndView(); // 모델엔뷰 객체 생성 (내부확인 해보기)
		mav.setViewName("board/view"); // 뷰의 이름
		mav.addObject("dto", boardService.read(bno)); // 오브젝트 저장.
		return mav;
	}
	
	//게시글 수정
	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	public String update(@ModelAttribute BoardVO vo)throws Exception{
		boardService.update(vo);
		return "redirect:list.do";
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam int bno)throws Exception{
		boardService.delete(bno);
		return "redirect:list.do";
	}
	
		
}
