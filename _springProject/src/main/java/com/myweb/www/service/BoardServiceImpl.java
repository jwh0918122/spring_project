package com.myweb.www.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVo;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	private BoardDAO bdao;
	private CommentService csv;
	private FileDAO fdao;

	@Autowired
	public BoardServiceImpl(BoardDAO bdao, CommentService csv, FileDAO fdao) {
		this.bdao = bdao;
		this.csv = csv;
		this.fdao = fdao;

	}

//	@Override
//	public int write(BoardVO bvo) {
//		return bdao.insert(bvo);
//	}

	@Override
	public List<BoardVO> getList() {
		return bdao.selectAll();
	}

	@Transactional
	@Override
	public BoardVO detail(long bno) {
		bdao.readCount(bno);
		return bdao.selectOne(bno);
	}

	@Transactional
	@Override
	public int modify(BoardDTO bdto) {
		int isOk = bdao.update(bdto.getBvo());
		if (bdto.getFlist() == null) {
			return isOk;
		} else if (isOk > 0 && bdto.getFlist().size() > 0) {
			for (FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk *= fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

//	@Override
//	public BoardVO SelectOneForModify(long bno) {	
//		return bdao.SelectOneForModify(bno);
//	}

	@Transactional
	@Override
	public int remove(long bno) {
		csv.deleteCommentAll(bno);// 게시글 지우기 전 댓글 먼저 지우기
		fdao.deleteFileAll(bno);// 게시글 지우기 전 파일 먼저 지우기
		return bdao.delete(bno);
	}

	@Transactional
	@Override
	public List<BoardVO> getList(PagingVo pagingVO) {
		// 파일 수 업데이트
		bdao.updateFileCnt();

		// 댓글 수 업데이트
		bdao.updateCmtQty();

		List<BoardVO> list = bdao.getList(pagingVO);
		return list;
	}

	@Override
	public int getTotalCount(PagingVo pagingVO) {
		return bdao.getTotalCount(pagingVO);
	}

	@Override
	public int write(BoardDTO bdto) {
		/* bvo, flist 가져와서 각자 db에 저장 */

		// 기존 메서드 활용
		int isUp = bdao.insert(bdto.getBvo()); // bvo 등록

		if (bdto.getFlist() == null) {
			return isUp;
		} else if (isUp > 0 && bdto.getFlist().size() > 0) {// bvo가 잘 등록되었고, 등록할 파일이 존재 한다면
			long bno = bdao.selectOneBno(); // 가장 마지막에 등록된 bno(방금 등록된)

			// 모든 파일에 bno를 세팅
			for (FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isUp *= fdao.insertFile(fvo); //파일 등록
			}
		}

		return isUp;
	}

	@Override
	public List<FileVO> getFileList(long bno) {
		return fdao.getFileList(bno);
	}

	// 수정에서 파일 삭제 버튼
	@Override
	public int fileDelete(String uuid) {
		return fdao.fileDelete(uuid);
	}

}
