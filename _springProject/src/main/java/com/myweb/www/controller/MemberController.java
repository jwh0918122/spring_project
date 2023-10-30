package com.myweb.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/**")
@Controller
public class MemberController {
	private MemberService msv;
	private BCryptPasswordEncoder bcEncoder;
	
	@Autowired
	public MemberController(MemberService msv,BCryptPasswordEncoder bcEncoder) {
		this.msv = msv;
		this.bcEncoder =bcEncoder;
	}

	// 들어온 매핑 이름이랑 나갈 jsp이름이 같으면 void로 해도 됨
	@GetMapping("/register")
	public void register() {
		
	}

	@PostMapping("/register")
	public String signUp(MemberVO mvo) {
		
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));//비밀번호 암호화
		int isOk=msv.register(mvo);
		return "index";
	}
	
	@GetMapping("login")
	public void loginPage() {
		
	}
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {//LoginFailureHandler에서 온거
		//로그인 실패 시 다시 로그인 페이지로 돌아와 오류 메시지 전송
		//다시 로그인 유도
		log.info(">>>> errMsg >>"+ request.getAttribute("errMsg"));
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/member/login";
	}
	
}
