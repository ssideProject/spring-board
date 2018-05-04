package com.app.spring.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.spring.model.dao.product.CartDAO;
import com.app.spring.model.dto.product.CartVO;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	CartDAO cartDAO;
	
	@Override
	public void insert(CartVO vo) {
		cartDAO.insert(vo);
	}

	@Override
	public List<CartVO> listCart(String userId) {
		return cartDAO.listCart(userId);
	}

	@Override
	public void delete(int cartId) {
		cartDAO.delete(cartId);
	}

	@Override
	public void modifyCart(CartVO vo) {
		cartDAO.modifyCart(vo);
	}

	@Override
	public int sumMoney(String userId) {
		return cartDAO.sumMoney(userId);
	}

	@Override
	public int countCart(int productId, String userId) {
		return cartDAO.countCart(productId, userId);
	}

	@Override
	public int updateCart(CartVO vo) {
		return cartDAO.updateCart(vo);
	}

}
