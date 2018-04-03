package com.app.spring.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.spring.model.dao.BoardDAO;
import com.app.spring.model.dto.BoardVO;

@Service("bookService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired // @Inject
	BoardDAO boardDao;
	
	@Override
	public void create(BoardVO vo) throws Exception {
		String title =vo.getTitle();
		String content = vo.getContent();
		String writer = vo.getWriter();
		
		//*태그문자 처리 ("<"  ->  "&lt" / ">"    ->   "&gt")
		//replace (A,B) A를 B로 변경.
		title = title.replace("<", "&lt");
		title = title.replace("<", "&gt");
		writer = writer.replace("<", "&lt");
		writer = writer.replace(">", "&gt");
		// * 공백 처리	
		title = title.replace(" ", "&nbsp;&nbsp;");
		writer = writer.replaceAll(" ", "&ndsp;&nbsp");
		//* 줄바꿈 문자처리
		content = content.replace("\n", "<br>");
		// 그리고 저장.
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		boardDao.create(vo);

	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		boardDao.update(vo);

	}

	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);

	}

	@Override
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception {
	    return boardDao.listAll( start, end, searchOption, keyword);
	}

	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		long update_time =0; //세션에 저장된 조회시간 탐색
		
		if(session.getAttribute("update_time"+ bno) != null) { //세션읽어오기
			update_time = (long)session.getAttribute("update_time" + bno);
		}
		
		long current_time = System.currentTimeMillis(); //시스템의 시간을 current시간에 저장
		//일정 시간이 경과후 조회수 증가 처리 24*60*60*1000 (24시간)
		//시스템현재시간 - 열람시간 > 일정시간 (조회수 증가 가능하도록지정한시간)
		if(current_time - update_time > 5*1000) {
			boardDao.increaseViewcnt(bno);
			//세션에 시간을 저장 : "update_time_"bno는 다른변수와 중복되지 않게 명명한것.
			session.setAttribute("update_time" + bno, current_time);
			
		}
	}
	
		
	public int countArticle(String searchOption, String keyword) throws Exception {
	    return boardDao.countArticle(searchOption, keyword);
	}


}
