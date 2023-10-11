package com.ezen.myproject.controller;

import java.util.List;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.myproject.domain.CommentVo;
import com.ezen.myproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comment/*")
@Slf4j
public class CommentController {

	@Inject
	private CommentService csv;

	// ResponseEntity 객체 사용
	// @RequstBody : body값 추출
	// vlaue="mapping name", (post말고도 뒤에 나올 애들이 많아서(consumes등) value라고 써줌)
	// consumes:가져오는 데이터의 형태
	// produces : 보내는 데이터의 형태(나가는 데이터 타입 : MediaType.)
	// json : application, json text : text_plain

	// 나갈 자료형을 <>에 넣어줌(isOk)(리스트보낼때는 <Llist<CommentVo>>)
	@PostMapping(value = "/post", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> post(@RequestBody CommentVo cvo) {
		log.info(">>>> cvo >> " + cvo);
		// DB연결
		int isOk = csv.post(cvo);
		// 리턴시 response의 통신상태를 같이 리턴
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping(value = "/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentVo>> spread(@PathVariable("bno") int bno) { // path에 달고오는 파라미터 받는 법
		log.info(">>>>>>>>> comment List bno >> " + bno);
		// DB 요청
		List<CommentVo> list = csv.getList(bno);
		return new ResponseEntity<List<CommentVo>>(list, HttpStatus.OK);

	}
	
	@PutMapping(value = "/{cno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> edit(@PathVariable("cno") int cno,@RequestBody CommentVo cvo) {
		log.info(">>>>comment modify cno>> " + cno);
		log.info(">>>>comment modify cvo>> " + cvo);
		int isOk = csv.edit(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@DeleteMapping(value="/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("cno") int cno){
		log.info(">>>>comment remove cno>> "+cno);
		int isOk=csv.remove(cno);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK)
				: new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
