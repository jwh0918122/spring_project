package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVo;
import com.myweb.www.handler.PagingHandler;

public interface CommentService {

	int addComment(CommentVO cvo);

//	List<CommentVO> getList(long bno);

	int remove(long cno);

	void deleteCommentAll(long bno);

	int modify(CommentVO cvo);

	PagingHandler getList(long bno, PagingVo pgvo);

}
