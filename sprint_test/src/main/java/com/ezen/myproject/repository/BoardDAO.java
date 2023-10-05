package com.ezen.myproject.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.myproject.domain.BoardVo;

public interface BoardDAO {

	int insert(BoardVo bvo);

	List<BoardVo> getList();

	BoardVo getDetail(int bno);

	void readCount(@Param("bno") int bno,@Param("cnt") int cnt);//파라미터를 2개 이상 달로 가려면 @Param 이용

	int modify(BoardVo bvo);

	int remove(int bno);

}
