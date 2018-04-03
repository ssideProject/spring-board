package com.app.spring.service.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.spring.model.dao.member.MemberDAO;
import com.app.spring.model.dto.member.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDao;
	
	
	
	@Override
	public void logout(HttpSession session) {
		// 세션 변수 개별 삭제
        // session.removeAttribute("userId");
        // 세션 정보를 초기화 시킴
        session.invalidate();
	}

	@Override
	public boolean loginCheck(MemberVO vo, HttpSession session) {
		boolean result = memberDao.loginCheck(vo); //DAO에게 파라매터를 하나 줄여서 준다.!!
		if(result) {
			MemberVO vo2 = viewMember(vo);
			//세션 변수 등록
			session.setAttribute("id", vo2.getId());
			session.setAttribute("name", vo2.getName());
		}
		return result;
	}
	
	@Override
	public MemberVO viewMember(MemberVO vo) {
		return memberDao.viewMember(vo);
	}
	
	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}

	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);
	}

	@Override
	public MemberVO detailMember(String id) {
		return memberDao.detailMember(id);
	}

	@Override
	public void deleteMember(String id) {
		memberDao.deleteMember(id);
	}

	@Override
	public void updateMember(MemberVO vo) {
        memberDao.updateMember(vo);
    }
	
	public boolean checkPasswd (String id, String passwd) {
		return memberDao.checkPasswd(id,passwd);
	}

}
