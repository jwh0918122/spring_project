package com.ezen.myproject.service;

import java.util.List;

import com.ezen.myproject.domain.BoardVo;

public interface BoardService {

	int register(BoardVo bvo);

	List<BoardVo> getList();

	BoardVo getDetail(int bno);

	int modify(BoardVo bvo);

	int remove(int bno);
	
}
