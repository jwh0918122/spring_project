package com.ezen.myproject.service;

import java.util.List;

import com.ezen.myproject.domain.BoardVo;
import com.ezen.myproject.domain.PagingVo;

public interface BoardService {

	int register(BoardVo bvo);

	List<BoardVo> getList(PagingVo pgvo);

	BoardVo getDetail(int bno);

	int modify(BoardVo bvo);

	int remove(int bno);

	int getTotalCount(PagingVo pgvo);
	
}
