package com.ezen.myproject.repository;

import java.util.List;

import com.ezen.myproject.domain.CommentVo;

public interface CommentDAO {

	int insert(CommentVo cvo);

	List<CommentVo> getList(int bno);

	int update(CommentVo cvo);

	int delete(int cno);

}
