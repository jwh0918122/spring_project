package com.ezen.myproject.service;

import com.ezen.myproject.domain.MemberVo;

public interface MemberService {

	int signUp(MemberVo mvo);

	MemberVo isUser(MemberVo mvo);

	int modify(MemberVo mvo);
	
}
