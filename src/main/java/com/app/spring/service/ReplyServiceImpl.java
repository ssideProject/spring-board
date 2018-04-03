package com.app.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.app.spring.model.dao.ReplyDAO;
import com.app.spring.model.dto.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	ReplyDAO replyDao;

	@Override
	public List<ReplyVO> list(Integer bno) {
		return replyDao.list(bno);
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

}
