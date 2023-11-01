package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberDAO mdao;

	public MemberServiceImpl(MemberDAO mdao) {
		this.mdao = mdao;
	}

	@Transactional
	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.insert(mvo); // 회원 등록
		return mdao.insertAuthInit(mvo.getEmail()); // 권한 주기
	}

	@Override
	public boolean updateLastLogin(String authEmail) {

		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public List<MemberVO> getList() {
		return mdao.selectAll();
	}

	@Override
	public MemberVO getDetail(String email) {
		return mdao.selectOne(email);
	}

	@Override
	public List<AuthVO> getAuthList(String email) {
		return mdao.selectAuths(email);
	}

	@Override
	public int Modify(MemberVO mvo) {
		return mdao.memberUpdate(mvo);
	}

	@Override
	public MemberVO adminModifyMvo(String email) {
		return mdao.selectOneModify(email);
	}

	@Override
	public int delMvo(String email) {
		
		int isOk=mdao.deleteAuthMvo(email);//권한 먼저 삭제
		if(isOk>0) {
			isOk = mdao.deleteMvo(email);
		}		
		return isOk;
	}

}
