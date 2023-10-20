package com.myweb.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
	private CommentService csv;

	@Autowired
	public CommentController(CommentService csv) {
		this.csv = csv;
	}

	// 댓글 등록
	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {
		int isOk = csv.addComment(cvo);

		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 댓글 리스트 출력
	@GetMapping(value = "/list/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentVO>> list(@PathVariable("bno") long bno) {
		List<CommentVO> list = csv.getList(bno);
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK);

	}

	// 댓글 삭제
	@DeleteMapping(value = "/remove/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("cno") long cno) {
		int isOk = csv.remove(cno);

		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
