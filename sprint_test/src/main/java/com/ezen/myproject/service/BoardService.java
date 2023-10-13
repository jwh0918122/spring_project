package com.ezen.myproject.service;

import java.util.List;

import com.ezen.myproject.domain.BoardDTO;
import com.ezen.myproject.domain.BoardVo;
import com.ezen.myproject.domain.PagingVo;

public interface BoardService {

	int register(BoardDTO bdto);

	List<BoardVo> getList(PagingVo pgvo);

	BoardVo getDetail(int bno);

	int modify(BoardVo bvo);

	int remove(int bno);

	int getTotalCount(PagingVo pgvo);

	
}
