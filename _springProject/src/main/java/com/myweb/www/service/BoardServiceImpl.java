package com.myweb.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVo;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	private BoardDAO bdao;
	private CommentService csv;
	
	@Autowired
	public BoardServiceImpl(BoardDAO bdao,CommentService csv) {
		this.bdao=bdao;
		this.csv=csv;
	}

	@Override
	public int write(BoardVO bvo) {
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		return bdao.selectAll();
	}

	@Override
	public BoardVO detail(long bno) {
		bdao.readCount(bno);
		return bdao.selectOne(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		return bdao.update(bvo);
	}

	@Override
	public BoardVO SelectOneForModify(long bno) {
		return bdao.SelectOneForModify(bno);
	}

	@Override
	public int remove(long bno) {		
		csv.deleteCommentAll(bno);//게시글 지우기전 댓글 먼저 지우기
		return bdao.delete(bno);
	}

	@Override
	public List<BoardVO> getList(PagingVo pagingVO) {
		return bdao.getList(pagingVO);
	}

	@Override
	public int getTotalCount(PagingVo pagingVO) {
		return bdao.getTotalCount(pagingVO);
	}

}
