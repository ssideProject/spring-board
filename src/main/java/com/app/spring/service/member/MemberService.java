package com.app.spring.service.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.app.spring.model.dto.member.MemberVO;

public interface MemberService {
	public boolean loginCheck(MemberVO vo, HttpSession session);
	public MemberVO viewMember(MemberVO vo);
	public void logout(HttpSession session);
	public List<MemberVO> memberList();
	public void insertMember(MemberVO vo);
	public MemberVO detailMember(String id);
	public void deleteMember(String id);
	public void updateMember(MemberVO vo);
	public boolean checkPasswd(String id, String passwd);
}
