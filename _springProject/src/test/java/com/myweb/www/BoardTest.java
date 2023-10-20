package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class) // "SpringJUnit4ClassRunner.class"가 실행되게 하는 거
@ContextConfiguration(classes = { com.myweb.www.config.RootConfig.class })
public class BoardTest {

	@Inject
	private BoardDAO bdao;

	@Test
	public void insertBoard() {
		for (int i = 0; i < 300; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("test title" + i);
			bvo.setWriter("tester" + (int) ((Math.random() * 30) + 1));
			bvo.setContent("test content" + i);
			bdao.insert(bvo);
		}
	}
}
