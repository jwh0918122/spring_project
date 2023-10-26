package com.myweb.www.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVo;

public interface BoardService {

//	int write(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO detail(long bno);

	int modify(BoardDTO bdto);

//	BoardVO SelectOneForModify(long bno);

	int remove(long bno);

	List<BoardVO> getList(PagingVo pagingVO);

	int getTotalCount(PagingVo pagingVO);

	int write(BoardDTO boardDTO);

	List<FileVO> getFileList(long bno);

	int fileDelete(String uuid);






}
