package com.myweb.www.service;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	List<MemberVO> getList();

	MemberVO getDetail(String email);

	List<AuthVO> getAuthList(String email);

	int Modify(MemberVO mvo);

	MemberVO adminModifyMvo(String email);

	int delMvo(String email);

}
