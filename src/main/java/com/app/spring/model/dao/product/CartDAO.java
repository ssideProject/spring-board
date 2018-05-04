package com.app.spring.model.dao.product;

import java.util.List;

import com.app.spring.model.dto.product.CartVO;

public interface CartDAO {
	
	public void insert(CartVO vo);
	public List<CartVO> listCart(String userId);
	public void delete(int cartId);
	public void modifyCart(CartVO vo);
	public int sumMoney(String userId);
	public int countCart(int productId, String userId);
	public int updateCart(CartVO vo);
	
	
}
