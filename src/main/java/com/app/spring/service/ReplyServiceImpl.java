package com.app.spring.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.app.spring.model.dao.ReplyDAO;
import com.app.spring.model.dto.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	ReplyDAO replyDao;
	
	//1. 댓글입력
	public void create(ReplyVO vo) {
		replyDao.create(vo);
	}

	//2. 댓글목록
    public List<ReplyVO> list(Integer bno, int start, int end, HttpSession session) {
        List<ReplyVO> items = replyDao.list(bno, start, end);
        // 세션에서 현재 사용자 id값 저장
        String Id = (String) session.getAttribute("id");
        for(ReplyVO vo : items){
            if(vo.getSecretReply().equals("y")){
                if(Id == null){ 
                    vo.setReplytext("[로그인 안해서 안보여]");
                } else { 
                    String writer = vo.getWriter(); 
                    String replyer = vo.getReplyer();
                    if(!Id.equals(writer) && !Id.equals(replyer)) {
                        vo.setReplytext("[내가 쓴 댓글이 아니야]");
                    }
                }
            }
        }
        return items; 
    }
	
    
    //3. 댓글 상세보기
    public ReplyVO detail(Integer rno) {
    	return replyDao.detail(rno);
    }
    
	@Override
	public void update(ReplyVO vo) {
		replyDao.update(vo);
	}

	@Override
	public void delete(Integer rno) {
		replyDao.delete(rno);
	}

	@Override
	public int count(Integer bno) {
		return replyDao.count(bno);
	}

}
