package com.app.spring.model.dao.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.spring.model.dto.member.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public boolean loginCheck(MemberVO vo) {
		String name = sqlSession.selectOne("member.loginCheck",vo);
		return (name == null) ? false: true;
	}

	@Override
	public MemberVO viewMember(MemberVO vo) {
		return sqlSession.selectOne("member.viewMember", vo);
	}
	

	@Override
	public void logout(HttpSession session) {
	} // 로그아웃은 서비스 단에서 끝을 내고 데이터엑세스 까지는 안가지고 오는게 맞는듯.
	
	@Override
	public List<MemberVO> memberList() {
		return sqlSession.selectList("member.memberList");
	}

	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert("member.insertMember", vo);
	}

	@Override
	public MemberVO detailMember(String id) {
		return sqlSession.selectOne("member.detailMember", id);
	}

	@Override
	public void deleteMember(String id) {
		sqlSession.delete("member.deleteMember", id);

	}

	// 04. 회원 정보 수정 처리
    @Override
    public void updateMember(MemberVO vo) {
        sqlSession.update("member.updateMember", vo);
 
    }

    // 회원의 비밀번호가 맞는지를 보는것이다. 그런데
    // 요기능을 DAO에서 하는것이 맞는거겠지?? 다른법도 있을까? 기능구현이니까 DAO말고 SERVICE로 넘기게 된다면.
    // 음... MAP 변수를 하나 더 만들어서 전해줘야하니 번거롭기도 할꺼같군.
    public boolean checkPasswd(String id, String passwd) {
    	boolean result = false;
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("id", id);
    	map.put("passwd", passwd);
    	int count = sqlSession.selectOne("member.checkPasswd", map);
    	if(count ==1) result = true;
    	return result;
    }

}
