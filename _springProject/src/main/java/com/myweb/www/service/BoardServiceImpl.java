package com.myweb.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	private BoardDAO bdao;
	
	@Autowired
	public BoardServiceImpl(BoardDAO bdao) {
		this.bdao=bdao;
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
		return bdao.delete(bno);
	}

}
