package com.myweb.www.handler;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVo;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PagingHandler {

	// 1~10 , 11~20, 21~30
	private int startPage;// 화면에 보여지는 시작 페이지네이션 번호
	private int endPage;// 화면에 보여지는 끝 페이지네이션 번호

	private int realEndPage; // 페이지네이션 진짜 진짜 끝번호;
	private boolean prev, next;// 이전, 다음의 존재 여부
	private int totalCount; // 총 게시글 수
	private PagingVo pgvo;

	private List<CommentVO> cmtList;

	// 현재 페이지 값 가져오기 용도 / totalCount DB에서 조회 매개변수로 입력받기
	public PagingHandler(PagingVo pgvo, int totalCount) {
		// pgvo.qty : 한 페이지에 보이는 게시글의 수 10개
		this.pgvo = pgvo;
		this.totalCount = totalCount;

		// 1페이지부터 10페이지까지 어떤 페이지가 선택되도 endPage는 10
		// 1 2 3 4 ...10/10 나머지를 올려(ceil) 1로 만듬 * 10(화면에 표시되어야 하는 페이지네이션 개수)
		this.endPage = (int) Math.ceil(pgvo.getPageNo() / (double) 10) * 10;

		this.startPage = endPage - 9;

		// 전체 글 수 / 한 페이지에 표시되는 게시글 수 => 올림(ceil)
		this.realEndPage = (int) Math.ceil(totalCount / (double) pgvo.getQty());

		if (endPage > realEndPage) {
			this.endPage = this.realEndPage;
		}

		// startPage는 1,11,21,31...
		this.prev = this.startPage > 1;

		this.next = this.endPage < realEndPage;
	}

	public PagingHandler(PagingVo pgvo, int totalCount, List<CommentVO> cmtList) {

		this(pgvo, totalCount);
		this.cmtList = cmtList;

	}
}
