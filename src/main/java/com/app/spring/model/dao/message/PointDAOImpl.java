package com.app.spring.model.dao.message;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO{

	@Inject
	SqlSession sqlSession;
	public void updatePoint(String userid, int upoint) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("upoint", upoint);
		sqlSession.update("point.updatePoint", map);
	}
}
