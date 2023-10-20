package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.CommentVO;

public interface CommentService {

	int addComment(CommentVO cvo);

	List<CommentVO> getList(long bno);

	int remove(long cno);

	void deleteCommentAll(long bno);

}
