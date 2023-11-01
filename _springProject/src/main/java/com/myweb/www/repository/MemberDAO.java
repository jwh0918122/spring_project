package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	int insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	List<MemberVO> selectAll();

	MemberVO selectOne(String email);

	int memberUpdate(MemberVO mvo);

	MemberVO selectOneModify(String email);

	int deleteAuthMvo(String email);

	int deleteMvo(String email);
	
}

