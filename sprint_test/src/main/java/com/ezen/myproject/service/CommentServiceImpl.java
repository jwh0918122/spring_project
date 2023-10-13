package com.ezen.myproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.myproject.domain.CommentVo;
import com.ezen.myproject.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
	
	@Inject
	private CommentDAO cdao;

	@Inject
	private BoardService bsv;
	
	@Override
	public int post(CommentVo cvo) {
		// TODO Auto-generated method stub
		int isOk =cdao.insert(cvo);
		return isOk;
	}

	@Override
	public List<CommentVo> getList(int bno) {
		// TODO Auto-generated method stub
		return cdao.getList(bno);
	}

	@Override
	public int edit(CommentVo cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}

	@Override
	public int remove(int cno) {
		// TODO Auto-generated method stub
		return cdao.delete(cno);
	}
	

	
}
