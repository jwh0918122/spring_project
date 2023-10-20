package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString
public class PagingVo {
	private int pageNo; // 현재 페이지 번호
	private int qty;// 한 페이지에 보여져야 하는 게시글 수

	private String type; // 검색 타입
	private String keyword; // 검색어

	public PagingVo() {
		this(1, 10);
	}

	public PagingVo(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}

	// 시장 페이지 번호 구하기
	// limit 시작,qty (Mapper에 입력할 쿼리문)
	// 1페이지/2페이지 /3페이지
	// 0,10 / 10,10 / 20,10...
	public int getPageStart() {
		return (this.pageNo - 1) * qty;
	}

	// 타입의 형태를 여러가지 형태로 복합적인 검색을 하기 위해서
	// 타입의 키워드 t,c,w,tx,tw,cw,tcw
	// 복합타입을 배열로 나누기 위해 사용
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}

}
