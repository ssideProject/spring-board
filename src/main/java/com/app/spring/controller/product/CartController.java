package com.app.spring.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.spring.controller.member.MemberController;
import com.app.spring.model.dto.product.CartVO;
import com.app.spring.service.product.CartService;

@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	
	private static final Logger logger =  Logger.getLogger(MemberController.class);
	
	@Autowired
	CartService cartService;
	
	//장바구니에 추가
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute CartVO vo, HttpSession session) {
		String id = (String) session.getAttribute("id");
        vo.setUserId(id);
        // 장바구니에 기존 상품이 있는지 검사
        logger.info("여기까지 잘 나오는거지??");
        int count = cartService.countCart(vo.getProductId(), id);
        //count == 0 ? cartService.updateCart(vo) : cartService.insert(vo);
        if(count == 0){
            // 없으면 insert
            cartService.insert(vo);
        } else {
            // 있으면 update
            cartService.updateCart(vo);
        }
        return "redirect:/shop/cart/list.do";
		
	}
	
	
	//장바구니 목록입니다.
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		String userId = (String) session.getAttribute("userId");
		Map<String,Object> map = new HashMap<String, Object>();
		List<CartVO> list = cartService.listCart(userId); //장바구니 정보입니다.
		int sumMoney = cartService.sumMoney(userId); //장바구니 전체 금액 호출
		
		//장바구니 전체 금액에 따라 배송비 구분
		//배송료 (10만원 이상 => 무료)
		
		int fee = (sumMoney >= 100000) ? 0 : 2500; //삼항연산자이다. 명칭을 까먹고잇었음.
		
		map.put("list", list);
		map.put("count", list.size());
		map.put("sumMoney", sumMoney);
		map.put("fee", fee);
		map.put("allSum", sumMoney+fee);
		
		mav.setViewName("shop/cartList");
		mav.addObject("map", map);
		return mav;
		
	}
	
	//장바구니 삭제
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cartId) {
		cartService.delete(cartId);
		return "redirect:/shop/cart/list.do";
	}
	
	
	//장바구니 수정
	@RequestMapping("update.do")
	public String update(@RequestParam int[] amount, @RequestParam int[] productId, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		for(int i=0; i<productId.length; i++) {
			CartVO vo = new CartVO();
			vo.setUserId(userId);
			vo.setAmount(amount[i]);
			vo.setProductId(productId[i]);
			cartService.modifyCart(vo);
		}
		
		return "redirect:/shop/cart/list.do";
		
	}
	
}
