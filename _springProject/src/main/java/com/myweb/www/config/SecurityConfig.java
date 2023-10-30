package com.myweb.www.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import com.myweb.www.security.CustomAuthMemberService;
import com.myweb.www.security.LoginFailureHandler;
import com.myweb.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// security package를 생성하여 사용자 핸들러 생성

	// 비밀번호 암호화 객체 빈 생성
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// SuccessHandler 빈 객체 생성 => 사용자가 생성
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new LoginSuccessHandler();
	}

	// failureHandler 빈 객체 생성 => 사용자가 생성
	@Bean
	public AuthenticationFailureHandler authFailureHandler() {
		return new LoginFailureHandler();
	}

	// userDetail 빈 객체 생성 => 사용자가 생성
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthMemberService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService())
		.passwordEncoder(bcPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		/*http에 승인요청*/
		//permitAll은 모든 사용자가 사용 가능
		http.authorizeRequests()
		.antMatchers("/member/list").hasRole("ADMIN") // /member/list페이지는 ADMIN만 가능
		.antMatchers("/","/board/list","/board/detail","/resources/**","/member/register","/member/login","/upload/**","/comment/**").permitAll()
		.anyRequest().authenticated(); //나머지는 인증된 사용자만 사용할 수 있게 처리
	
		//커스텀 로그인 페이지 구성		
		http.formLogin()
		.usernameParameter("email") //usernameParameter은 id에 해당하는거(email을 id로 사용할거임)
		.passwordParameter("pwd")
		.loginPage("/member/login") //Controller에 주소요청 맵핑도 같이 꼭 적어줘야 함.
		.successHandler(authSuccessHandler()) //성공 시 authSuccessHandler메서드 타라
		.failureHandler(authFailureHandler()); //실패 시 authFailureHandler메서드 타라
		
		//로그아웃 페이지
		http.logout()
		.logoutUrl("/member/logout") //반드시 method="post"(nav.jsp에 있음)
		.invalidateHttpSession(true) //기존 세션 아이디를 끊을 것인지 여부
		.deleteCookies("JSESSIONID") //쿠키도 지워줌
		.logoutSuccessUrl("/"); //로그아웃 완료 후 이동할 url
		
	}

}
