package com.myweb.www.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMethodMappingNamingStrategy;

import com.myweb.www.service.MemberService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Getter
	@Setter
	private String authEmail;

	@Getter
	@Setter
	private String authUrl;

	// redirect로 데이터(reqCache)를 가져가는 역할(리다이렉트 스트레터지)
	private RedirectStrategy rdstg = new DefaultRedirectStrategy();
	// 실제 로그인 정보,경로 등의 데이터를 저장
	private RequestCache reqCache = new HttpSessionRequestCache();

	@Inject
	private MemberService msv;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		setAuthEmail(authentication.getName()); // 권한을 가지는 네임(이메일)
		setAuthUrl("/board/list"); // 성공하고 나서 어디로 갈지

		boolean isOk = msv.updateLastLogin(getAuthEmail());

		// onAuthenticationSuccess로 왔다는 건 이미 내부에서 로그인 세션 저장됐다는 것.
		HttpSession ses = request.getSession();
		log.info("LoginSuccess >>> ses >>> " + ses.toString());

		if (!isOk || ses == null) {// 시큐리티에서 로그인 값이 없다면 null로 저장될 수 있음.
			return;
		} else { // 로그인이 잘 됐다면
			// 시큐리에네서 로그인을 시도하면 시도한 로그인 기록이 남게됨.
			// 이전 시도한 시큐리티의 로그인 인증 실패 기록 삭제
			ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		
		//이전 방문한 페이지 기록이 있으면 그 페이지로 가고, 없으면 리스트로 가라
		SavedRequest saveReq = reqCache.getRequest(request, response);
		rdstg.sendRedirect(request, response,(saveReq != null ? saveReq.getRedirectUrl():getAuthUrl()));
	}

}
