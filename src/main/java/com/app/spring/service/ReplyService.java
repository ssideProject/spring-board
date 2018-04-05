package com.app.spring.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.app.spring.model.dto.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> list(Integer bno, int start, int end, HttpSession session);
	public void create(ReplyVO vo);
	public void update(ReplyVO vo);
	public void delete(ReplyVO vo);
	public int count(int bno);
}
