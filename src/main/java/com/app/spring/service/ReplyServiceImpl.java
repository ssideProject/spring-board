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

	@Override
    public List<ReplyVO> list(Integer bno, int start, int end, HttpSession session) {
        List<ReplyVO> items = replyDao.list(bno, start, end);
        // 세션에서 현재 사용자 id값 저장
        String Id = (String) session.getAttribute("id");
        for(ReplyVO vo : items){
            
            if(vo.getSecretReply().equals("y")){
                if(Id == null){ 
                    vo.setReplytext("비밀 댓글입니다.1");
                } else { 
                    String writer = vo.getWriter(); 
                    String replyer = vo.getReplyer(); 
                    
                    if(!Id.equals(writer) && !Id.equals(replyer)) {
                        vo.setReplytext("비밀 댓글입니다.2");
                    }
                }
            }
        }
        return items; 
    }
	

	@Override
	public void create(ReplyVO vo) {
		replyDao.create(vo);
		
	}

	@Override
	public void update(ReplyVO vo) {
		//
		
	}

	@Override
	public void delete(ReplyVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count(int bno) {
		return replyDao.count(bno);
	}

}
