package com.app.spring.service.product;

import java.util.List;

import com.app.spring.model.dto.product.ProductVO;

public interface ProductService {
	public List<ProductVO> listProduct();
	public ProductVO detailProduct(int productId);
	public void updateProduct(ProductVO vo);
	public void deleteProduct(int productId);
	
}
