package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVo;

public interface CommentDAO {

	int insert(CommentVO cvo);

//	List<CommentVO> selectAll(long bno);

	int delete(long cno);

	void deleteCommentAll(long bno);

	int update(CommentVO cvo);

	int selectOneBnoTotalCount(long bno);

	List<CommentVO> selectListPaging(@Param("bno") long bno,@Param("pgvo") PagingVo pgvo);

	



}
