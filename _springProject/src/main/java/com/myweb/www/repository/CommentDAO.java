package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> selectAll(long bno);

	int delete(long cno);

	void deleteCommentAll(long bno);



}
