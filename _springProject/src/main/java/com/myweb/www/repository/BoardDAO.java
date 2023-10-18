package com.myweb.www.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myweb.www.domain.BoardVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectAll();

	BoardVO selectOne(long bno);

	int update(BoardVO bvo);

	BoardVO SelectOneForModify(long bno);

	int delete(long bno);

	void readCount(long bno);

}
