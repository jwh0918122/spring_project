package com.ezen.myproject.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myproject.domain.MemberVo;
import com.ezen.myproject.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	@Inject
	private MemberService msv;	
	
	@GetMapping("/signup")
	public String signupGet() {
		
		return"member/signup";
	}
	
	@PostMapping("/signup")
	public String signupPost(MemberVo mvo) {
		log.info(">>>>>>>회원가입 객체 >>"+mvo);
		int isOk=msv.signUp(mvo);
		return "index";
	}
	
	@GetMapping("/login")
	public String loginGet() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String loginPost(MemberVo mvo,HttpServletRequest request,Model m) {
		log.info(">>>> 로그인 사용자 >> "+mvo);
		//mvo 객체를 DB에서 일치하는지 체크
		MemberVo loginMvo = msv.isUser(mvo);
		
	
		if(loginMvo!=null) {	//DB에서 가져온 loginMvo가 null이 아니라면 세션에 저장
			HttpSession ses = request.getSession();//있으면 기존꺼 가져오고, 없으면 새로 생성
			ses.setAttribute("ses", loginMvo); //세션에 로그인 객체 저장
			ses.setMaxInactiveInterval(60*10); //로그인 유지 시간(60초 * 10 = 10분)
		}else { //DB에서 가져온 loginMvo가 null이면
			m.addAttribute("msg_login", 1);
		}
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model m) {
		//세션 객체 삭제
		request.getSession().removeAttribute("ses");
		
		//세션 끊기
		request.getSession().invalidate();
		
		m.addAttribute("msg_logout", 1);
		return "index";
		
	}	
	@GetMapping("/modify")
	public String modifyGet() {
		return "/member/modify";
	}
	@PostMapping("/modify")
	public String modifyPost(MemberVo mvo,RedirectAttributes reAttr) {
		log.info(">>>>> modify mvo >>"+mvo);
		int isOk=msv.modify(mvo);
		reAttr.addFlashAttribute("msg_modify", 1);
		return "redirect:/member/logout"; 
	}
}
