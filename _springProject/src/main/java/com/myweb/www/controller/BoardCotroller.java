package com.myweb.www.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVo;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardCotroller {
// 폴더명 : board / mapping : board
	// mpapping => /board/register
	// 목적지 => /board/register

	private BoardService bsv;

	private FileHandler fh;

	@Autowired
	public BoardCotroller(BoardService bsv, FileHandler fh) {
		this.bsv = bsv;
		this.fh = fh;
	}

	// 글쓰기 jsp로 이동
	@GetMapping("/register")
	public String register() {// jsp에서 온 매핑이랑 뷰로 들어가는 매핑이 같아서(이름이 같아서) void로 하면 왔던 곳으로 가라고 할 수 있음
		return "/board/register"; // 이렇게 해도 됨(뷰로 들어가는 매핑)
	}

	// 글등록
	@PostMapping("/register")
	public String write(BoardVO bvo, @RequestParam(name = "files", required = false) MultipartFile[] files) {
		log.info(">>> bvo >>> files " + bvo + "  " + files);
		List<FileVO> flist = null;
		// file upload handler 생성
		if (files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		int isOk = bsv.write(new BoardDTO(bvo, flist));
		return "redirect:/board/list"; // :컨트롤러에서 list로 getMapping되어있는 메서드로 이동
	}

//	// 리스트 출력
//	@GetMapping("/list")
//	public String list(Model model) {
//		List<BoardVO> list = bsv.getList();
//		model.addAttribute("list", list);
//
//		return "/board/list";
//	}
	// 리스트 출력(paging 추가)
	@GetMapping("/list")
	public String list(Model model, PagingVo pagingVO) {
		log.info(">>>>>>pagingVO >>" + pagingVO);

		// 이렇게 하면 service에서 return값 설정해주면 됨
		model.addAttribute("list", bsv.getList(pagingVO));

		/* 페이징 처리 */
		// 총 페이지 갯수
		int totalCount = bsv.getTotalCount(pagingVO);
		PagingHandler ph = new PagingHandler(pagingVO, totalCount);
		model.addAttribute("ph", ph);
		return "/board/list";
	}

	// bvo 가지고 디테일 jsp 이동
	@GetMapping("/detail")
	public String detail(Model model, @RequestParam("bno") long bno) {
		BoardVO bvo = bsv.detail(bno);
		List<FileVO> flist = bsv.getFileList(bno);

		BoardDTO bdto = new BoardDTO(bvo, flist);

		model.addAttribute("bvo", bdto.getBvo());
		model.addAttribute("bdto", bdto);
		return "/board/detail";
	}

	// bno로 bvo찾은다음 model에 담아서 수정 페이지로 이동
	@GetMapping("/modify")
	public String modify(Model model, @RequestParam("bno") long bno) {
		BoardVO bvo = bsv.detail(bno);
		List<FileVO> flist = bsv.getFileList(bno);

		BoardDTO bdto = new BoardDTO(bvo, flist);

		model.addAttribute("bdto", bdto);
		return "/board/modify";
	}

	// 수정
	@PostMapping("/modify")
	public String modify(BoardVO bvo,RedirectAttributes red,
			@RequestParam(name="files", required = false) MultipartFile[] files) {
		
		List<FileVO> flist = null;
		if(files[0].getSize()>0) { //files 0번지 배열에 파일이 있다면 
			flist=fh.uploadFiles(files);
		}
		int isOk = bsv.modify(new BoardDTO(bvo, flist));

//		이렇게 하면 return "redirect:/board/detail?bno="+bvo.getBno(); 하는 거와 같음
		red.addAttribute("bno", bvo.getBno());
		return "redirect:/board/detail";
	}

	// 삭제
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") long bno, RedirectAttributes red) {
		int reisOk = bsv.remove(bno);
		red.addFlashAttribute("reisOk", reisOk);
		return "redirect:/board/list";
	}

	// 파일 삭제
	@DeleteMapping(value="/fileDelete/{uuid}",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> fileDelete(@PathVariable("uuid") String uuid) {
		int isOk = bsv.fileDelete(uuid);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
