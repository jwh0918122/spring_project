package com.ezen.myproject.service;

import java.util.List;

import com.ezen.myproject.domain.CommentVo;

public interface CommentService {

	int post(CommentVo cvo);

	List<CommentVo> getList(int bno);

	int edit(CommentVo cvo);

	int remove(int cno);
	

}
