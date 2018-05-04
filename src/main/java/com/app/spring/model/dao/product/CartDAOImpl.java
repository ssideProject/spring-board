package com.app.spring.model.dao.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.spring.model.dto.product.CartVO;

@Repository
public class CartDAOImpl implements CartDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void insert(CartVO vo) {
		sqlSession.insert("cart.insertCart", vo);
	}

	@Override
	public List<CartVO> listCart(String userId) {
		return sqlSession.selectList("cart.listCart", userId);
	}

	@Override
	public void delete(int cartId) {
		sqlSession.delete("cart.deleteCart", cartId);
	}

	@Override
	public void modifyCart(CartVO vo) {
		sqlSession.update("cart.modifyCart", vo);
	}

	@Override
	public int sumMoney(String userId) {
		sqlSession.selectOne("cart.sumMoney", userId);
		return sqlSession.selectOne("cart.sumMoney", userId);
	}

	@Override
	public int countCart(int productId, String userId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("productId", productId);
		map.put("userId", userId);
		return sqlSession.selectOne("cart.countCart", map);
	}

	@Override
	public int updateCart(CartVO vo) {
		return sqlSession.update("cart.sumCart", vo);
	}


}
