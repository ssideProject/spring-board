package com.app.spring.service.message;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.spring.model.dao.message.MessageDAO;
import com.app.spring.model.dao.message.PointDAO;
import com.app.spring.model.dto.message.MessageVO;

@Service
public class MessageServiceImpl implements MessageService{
	@Inject
	MessageDAO messageDAO;
	
	@Inject
	PointDAO pointDAO;
	
	//메시지 작성(DB저장, 포인트적립)
	@Transactional // 트랜잭션 대상 매서드
	public void addMessage(MessageVO vo) {
		//공통업무 - 로그확인
		//핵심업무 - 메시지저장, 회원 포인트 적립
		//메시지를 테이블에 저장
		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
	}
	
	public MessageVO readMessage(String userid , int mid) {
		return null;
	}
}
