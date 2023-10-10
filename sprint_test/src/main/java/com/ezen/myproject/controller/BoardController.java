package com.ezen.myproject.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myproject.domain.BoardVo;
import com.ezen.myproject.domain.PagingVo;
import com.ezen.myproject.handler.PagingHandler;
import com.ezen.myproject.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*") //jsp에서 받아올때 url 앞에 /board/붙어있는건 여기서 처리한다
@Controller
public class BoardController {
	
	@Inject  // new로 생성자 만들것과 같은 것(@autoWired써도 됨)
	private BoardService bsv; 
	 
	
	
	@GetMapping("/register")
	public String boardRegisterGet() {
		
		return "/board/register"; //.jsp는 생략
	}
	
	@PostMapping("/register")
	public String register(BoardVo bvo,RedirectAttributes reAttr) {
		log.info(">>>>>>>>>>>"+bvo.toString());
		int isOk = bsv.register(bvo);
		log.info(">>>>>>> board register >> "+(isOk>0?"OK":"FAIL"));
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(Model model,PagingVo pgvo) {  //Model 객체는 내보낼 것이 있을 때(model.addAttribute)
		log.info(">>>>> PaginVo >>> "+pgvo);
		//getList(pgvo);  pgvo들고가서 limit해주는거
		List<BoardVo> list = bsv.getList(pgvo);
//		log.info(">>>>>>>>>board getList >>"+list);
		model.addAttribute("list", list); //request객체의 setAttribute를 Model객체가 해주는 거
		int totalCount = bsv.getTotalCount(pgvo);//등록
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		model.addAttribute("ph", ph);
		return "/board/list";
	}
	
	@GetMapping({"/detail","/modify"})
	//return값을 String이 아닌 void로 하면 온데로 감
	//detail은 detail.jsp로, modify는 modify.jsp로 가야하기 때문에
	public void datail(Model model, @RequestParam("bno")int bno) { //model은 보내주는 거, bno는 파라미터
		log.info(">>>>>> board detail bno >>"+"bno");
		
		BoardVo bvo=bsv.getDetail(bno);
		model.addAttribute("bvo", bvo);
	
	}

	
	@PostMapping("/modify")
	public String modify(BoardVo bvo,RedirectAttributes reAttr) {
		log.info(">>>>>>>>>>>"+bvo.toString());
		int isOk=bsv.modify(bvo);
		log.info(">>>>> board modify >>"+(isOk>0?"OK":"FAIL"));
		return "redirect:/board/detail?bno="+bvo.getBno(); //detail.jsp로 갈건데 내부 list메서드 타고 나서 가라
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")int bno,RedirectAttributes reAttr) {
		log.info(">>>>>> board remove bno >>"+"bno");
		int isOk=bsv.remove(bno);
		reAttr.addFlashAttribute("isOk", isOk); //일회성 attribute
		return "redirect:/board/list";
	}
	
}
