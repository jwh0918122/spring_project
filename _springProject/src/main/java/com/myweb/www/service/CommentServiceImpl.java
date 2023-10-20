package com.myweb.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
	private CommentDAO cdao;
	
	@Autowired
	public CommentServiceImpl(CommentDAO cdao) {
		this.cdao =cdao;
	}

	@Override
	public int addComment(CommentVO cvo) {
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(long bno) {
		return cdao.selectAll(bno);
	}

	@Override
	public int remove(long cno) {
		return cdao.delete(cno);
	}

	@Override
	public void deleteCommentAll(long bno) {
		cdao.deleteCommentAll(bno);
	}
}
