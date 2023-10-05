package com.ezen.myproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 멤버변수 갖는 생성자

public class BoardVo {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String isDel;
	private String registerDate;
	private int read_count;
	
}
