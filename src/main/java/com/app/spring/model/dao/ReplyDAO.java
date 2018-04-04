package com.app.spring.model.dao;

import java.util.List;

import com.app.spring.model.dto.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> list(Integer bno);
	public void create(ReplyVO vo);
	public void update(ReplyVO vo);
	public void delete(Integer rno);
}
