package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardService {

	int write(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO detail(long bno);

	int modify(BoardVO bvo);

	BoardVO SelectOneForModify(long bno);

	int remove(long bno);

}
