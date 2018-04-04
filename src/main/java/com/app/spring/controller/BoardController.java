package com.app.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.spring.model.dto.BoardVO;
import com.app.spring.service.BoardService;
import com.app.spring.service.board.BoardPager;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired //@Inject도 가능하다.
	BoardService boardService;
	
	
	//게시글 목록
	@RequestMapping("list.do")//파라매터를 default값을 준 이유를 생각해보기 triger로 쓰이고 있다.
	public ModelAndView list(@RequestParam(defaultValue ="title") String searchOption,
								@RequestParam(defaultValue="") String keyword,
								@RequestParam(defaultValue="1") int curPage) throws Exception{ 
		
		//레코드의 갯수 계산
		int count = boardService.countArticle(searchOption, keyword);
		
		//페이지 나누기 관리 처리
		BoardPager	boardPager = new BoardPager(count, curPage);
		int start = boardPager.getPageBegin();
		int end = boardPager.getPageEnd();
		
		List<BoardVO> list = boardService.listAll(start, end, searchOption, keyword);
		
		//데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list); // list
	    map.put("count", count); // 레코드의 갯수
	    map.put("searchOption", searchOption); // 검색옵션
	    map.put("keyword", keyword); // 검색키워드
	    map.put("boardPager", boardPager);
		
	    
	    ModelAndView mav = new ModelAndView(); 
	    mav.addObject("map", map); // 맵에 저장된 데이터를 mav에 저장
	    mav.setViewName("board/list"); // 뷰를 list.jsp로 설정
	    
	    return mav; // list.jsp로 List가 전달된다.
	}
	
	
	//게시글 작성 화면
	@RequestMapping(value="write.do", method=RequestMethod.GET) //@RequestMapping("board/write.do")
	public String write(@ModelAttribute BoardVO vo) {
		return "board/write";
	}
	
	
	//게시글 작성 처리
	@RequestMapping(value="insert.do", method = RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo, HttpSession session) throws Exception{
		String writer = (String) session.getAttribute("id");
		String userName = (String) session.getAttribute("name");
		vo.setWriter(writer);
		vo.setUserName(userName);
		boardService.create(vo);
		return "redirect:list.do"; // "redirect:/list.do"와다른것인가??
	}
	
	//게시글 상세내용 조회+ 조회수가 올라간다.
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
