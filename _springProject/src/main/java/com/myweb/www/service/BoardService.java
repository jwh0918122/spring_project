package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVo;

public interface BoardService {

	int write(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO detail(long bno);

	int modify(BoardVO bvo);

	BoardVO SelectOneForModify(long bno);

	int remove(long bno);

	List<BoardVO> getList(PagingVo pagingVO);

	int getTotalCount(PagingVo pagingVO);

}
