package com.app.spring.model.dao.product;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.app.spring.model.dto.product.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO{
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ProductVO> listProduct() {
		return sqlSession.selectList("product.listProduct");
	}

	@Override
	public ProductVO detailProduct(int productId) {
		return sqlSession.selectOne("product.detailProduct",productId);
	}

	@Override
	public void updateProduct(ProductVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		
	}

}
