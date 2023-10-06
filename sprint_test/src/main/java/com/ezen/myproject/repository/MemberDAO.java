package com.ezen.myproject.repository;

import com.ezen.myproject.domain.MemberVo;

public interface MemberDAO {
	
	MemberVo getUser(String id);

	int signUp(MemberVo mvo);

	int update(MemberVo mvo);

	

}
