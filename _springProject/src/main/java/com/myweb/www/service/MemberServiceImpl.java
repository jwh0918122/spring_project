package com.myweb.www.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;



@Service
public class MemberServiceImpl implements MemberService{
	
	private MemberDAO mdao;

	public MemberServiceImpl(MemberDAO mdao) {
		this.mdao = mdao;
	}

	@Transactional
	@Override
	public int register(MemberVO mvo) {
		int isOk =mdao.insert(mvo); //회원 등록
		return mdao.insertAuthInit(mvo.getEmail()); //권한 주기 
	}
	
	


	
	
}
