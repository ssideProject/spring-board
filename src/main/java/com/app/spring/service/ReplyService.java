package com.app.spring.service;

import java.util.List;

import com.app.spring.model.dto.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> list(Integer bno);
	public void create(ReplyVO vo);
	public void update(ReplyVO vo);
	public void delete(ReplyVO vo);
}
