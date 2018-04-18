package com.app.spring.model.dao.product;

import java.util.List;

import com.app.spring.model.dto.product.ProductVO;

public interface ProductDAO {
	public List<ProductVO> listProduct();
	public ProductVO detailProduct(int productId);
	public void updateProduct(ProductVO vo);
	public void deleteProduct(int productId);
	
}
