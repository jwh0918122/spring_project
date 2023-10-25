package com.myweb.www.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVo;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectAll();

	BoardVO selectOne(long bno);

	int update(BoardVO bvo);

//	BoardVO SelectOneForModify(long bno);

	int delete(long bno);

	void readCount(long bno);

	List<BoardVO> getList(PagingVo pagingVO);

	int getTotalCount(PagingVo pagingVO);

	long selectOneBno();

	void updateCmtQty();

	void updateFileCnt();
}
