package com.myweb.www.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public MemberController(MemberService msv, BCryptPasswordEncoder bcEncoder) {
		this.msv = msv;
		this.bcEncoder = bcEncoder;
	}

	// 들어온 매핑 이름이랑 나갈 jsp이름이 같으면 void로 해도 됨
	@GetMapping("/register")
	public void register() {

	}

	@PostMapping("/register")
	public String signUp(MemberVO mvo) {

		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));// 비밀번호 암호화
		int isOk = msv.register(mvo);
		return "index";
	}

	@GetMapping("login")
	public void loginPage() {
	}

	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {// LoginFailureHandler에서 온거
		// 로그인 실패 시 다시 로그인 페이지로 돌아와 오류 메시지 전송
		// 다시 로그인 유도
		log.info(">>>> errMsg >>" + request.getAttribute("errMsg"));
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/member/login";
	}

	//회원정보 리스트(관리자용)
	@GetMapping("list")
	public String listRegister(Model model) {
		List<MemberVO> list = msv.getList();

		for (MemberVO mvo : list) {
			mvo.setAuthList(msv.getAuthList(mvo.getEmail()));
		}
		model.addAttribute("list", list);
		return "/member/list";
	}	
	//상세페이지(일반유저)
	@GetMapping("detail")
	public String detailRegister(@RequestParam("email") String email, Model model) {
		MemberVO mvo = msv.getDetail(email);
		model.addAttribute("mvo", mvo);
		return "/member/detail";
	}
	//회원정보 수정(일반유저)
	@PostMapping("modify")
	public String modify(MemberVO mvo, HttpServletRequest req, HttpServletResponse res) {
		log.info("mvo>>>>>확인 >>> ",mvo);
		
			int isOk = msv.Modify(mvo);
			logout(req, res);			
		
		return "index";		
	}	
	//회원정보 수정(관리자용)
	@GetMapping("adminModify")
	public String adminModify(@RequestParam("email") String email, Model model){
		MemberVO mvo = msv.adminModifyMvo(email);
		model.addAttribute("mvo", mvo);
		return "/member/adminModify";		
	}
	
	//로그아웃
	private void logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(req, res, auth);
	}
	//회원 탈퇴
	@GetMapping("delMvo")
	public String dleMvo(@RequestParam("email") String email,HttpServletRequest req, HttpServletResponse res) {
		int isOk=msv.delMvo(email);
		logout(req,res);
		return"index";
	}
	
	

}
