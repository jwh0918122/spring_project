package com.ezen.myproject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.myproject.domain.BoardDTO;
import com.ezen.myproject.domain.BoardVo;
import com.ezen.myproject.domain.FileVo;
import com.ezen.myproject.domain.PagingVo;
import com.ezen.myproject.repository.BoardDAO;
import com.ezen.myproject.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao;

	@Override
	public int register(BoardDTO bdto) {
		log.info("register check2");
		// 기존 게시글에 대한 내용을 DB에 저장
		int isOk = bdao.insert(bdto.getBvo());
	
		// ----파일 저장 라인
		if (bdto.getFlist() == null) {
			//파일의 값이 null이면 저장 없음.
			isOk *= 1; //그냥 성공한걸로...
		}else { 
			//bvo의 값이 들어가고, 파일의 개수가 있다면
			if(isOk > 0 && bdto.getFlist().size()>0) {
				//fvo의 bno는 아직 설정되기 전.
				// 현재 시점에서 bno는 아직 결정되지 않음 => db insert ai에 의해 자동생성
				int bno = bdao.selectBno(); //방금 저장된 bno 가져오기
				//flist의 모든 fileVo에 방금 가져온 bno를 set
				for(FileVo fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					log.info(">>> fvo = {}",fvo);
					//파일 저장
					isOk *= fdao.insertFile(fvo);								
				}
			}
		}
		return isOk;
	}

	@Override
	public List<BoardVo> getList(PagingVo pgvo) {
		log.info("list check2");
		
		int isOk = bdao.updateCommentCount();
		isOk=bdao.updateFileCount();
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVo getDetail(int bno) {
		log.info("detail check2");
		// read_count +1
//		int cnt=1;
		bdao.readCount(bno, 1);
		return bdao.getDetail(bno);
	}

	@Override
	public int modify(BoardVo bvo) {
		log.info("modify check2");
		// 수정할 때 들어가는 부당 read_count 2개 빼주기
		// read_count -2
//		int cnt=-2;
		bdao.readCount(bvo.getBno(), -2);
		;
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
