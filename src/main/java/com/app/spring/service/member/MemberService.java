package com.app.spring.service.member;

import javax.servlet.http.HttpSession;

import com.app.spring.model.dto.member.MemberVO;

public interface MemberService {
	public boolean loginCheck(MemberVO vo, HttpSession session);
	public MemberVO viewMember(MemberVO vo);
	public void logout(HttpSession session);
}
