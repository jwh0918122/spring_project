package com.ezen.myproject.handler;

import com.ezen.myproject.domain.PagingVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingHandler {
	private int startPage;
	private int endPage;
	private int realEndPage;
	private boolean prev;
	private boolean next;
	private int totalCount;
	private PagingVo pgvo;
	
	public PagingHandler(PagingVo pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount=totalCount;
		// 1 2 3 ... 10 => 10 (pageNo 1~10까지는 endPage가 10)
		// 11 12 13 ... 20 => 20 (pageNo 11~20까지는 endPage가 20)
		this.endPage=
				(int)Math.ceil(pgvo.getPageNo()/(double)10)*10;
		this.startPage=endPage-9;
		
		realEndPage =(int)Math.ceil(totalCount/(double)pgvo.getQty());
		
		//rearPage는 10,20,30...형식으로만 구성.
		//realEndPage는 실제 마지막 페이지.
		if(realEndPage < this.endPage) {
			this.endPage=realEndPage;
		}
		
		//startPage는 1,11,21,31...
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.realEndPage;
	}
	
	
}
