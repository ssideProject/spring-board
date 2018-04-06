package com.app.spring.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.app.spring.model.dto.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ReplyVO> list(Integer bno, int start, int end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
        map.put("start", start);
        map.put("end", end);
		return sqlSession.selectList("reply.listReply", map);
	}

	@Override
	public void create(ReplyVO vo) {
		sqlSession.insert("reply.insertReply", vo);
	}

	@Override
	public void update(ReplyVO vo) {
		sqlSession.update("reply.updateReply", vo);
	}

	@Override
	public void delete(Integer rno) {
		sqlSession.delete("reply.deleteReply", rno);

	}

	@Override
	public ReplyVO detail(Integer rno) {
		return sqlSession.selectOne("reply.detailReply", rno);
	}

	@Override
	public int count(Integer bno) {
		return sqlSession.selectOne("reply.countReply", bno);
	}

}
