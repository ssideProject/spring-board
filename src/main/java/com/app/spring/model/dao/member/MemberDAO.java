package com.app.spring.model.dao.member;

import javax.servlet.http.HttpSession;

import com.app.spring.model.dto.member.MemberVO;

public interface MemberDAO {
	 // 01_01. 회원 로그인 체크
    public boolean loginCheck(MemberVO vo);
    // 01_02. 회원 로그인 정보
    public MemberVO viewMember(MemberVO vo);
    // 02. 회원 로그아웃
    public void logout(HttpSession session);
}
