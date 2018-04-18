package com.app.spring.service.product;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.app.spring.model.dao.product.ProductDAO;
import com.app.spring.model.dto.product.ProductVO;

@Service
public class ProductServiceImpl implements ProductService{
	@Inject
	ProductDAO productDAO;
	
	
	@Override
	public List<ProductVO> listProduct() {
		return productDAO.listProduct();
	}

	@Override
	public ProductVO detailProduct(int productId) {
		return productDAO.detailProduct(productId);
	}

	@Override
	public void updateProduct(ProductVO vo) {
		productDAO.updateProduct(vo);		
	}

	@Override
	public void deleteProduct(int productId) {
		productDAO.deleteProduct(productId);
	}

}
