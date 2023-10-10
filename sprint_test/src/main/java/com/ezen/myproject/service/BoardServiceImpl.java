package com.ezen.myproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.myproject.domain.BoardVo;
import com.ezen.myproject.domain.PagingVo;
import com.ezen.myproject.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;

	@Override
	public int register(BoardVo bvo) {
		log.info("register check2");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVo> getList(PagingVo pgvo) {
		log.info("list check2");
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVo getDetail(int bno) {
		log.info("detail check2");
		//read_count +1
//		int cnt=1;
		bdao.readCount(bno,1);
		return bdao.getDetail(bno);
	}

	@Override
	public int modify(BoardVo bvo) {
		log.info("modify check2");
		//수정할 때 들어가는 부당 read_count 2개 빼주기
		//read_count -2
//		int cnt=-2;
		bdao.readCount(bvo.getBno(),-2);;
		return bdao.modify(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("remove check2");
		return bdao.remove(bno);
	}

	@Override
	public int getTotalCount(PagingVo pgvo) {
		return bdao.TotalCount(pgvo);
	}
	

}
