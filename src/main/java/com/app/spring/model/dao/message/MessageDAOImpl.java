package com.app.spring.model.dao.message;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.app.spring.model.dto.message.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO{
	
	@Inject
	SqlSession sqlSession;
	
	public void create(MessageVO vo) {
		sqlSession.insert("message.create", vo);
	}
	
	public MessageVO readMessage(int mid) {
		return null;
	}
	
	public void updateMessage(int mid) {
		
	}
}
