package com.myweb.www.config;

import javax.servlet.Filter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;
import javax.sound.sampled.AudioFormat.Encoding;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		// encoding filter 설정
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8"); // 내부로 들어오는 데이터도 인코딩 설정(요청 시 request)
		encodingFilter.setForceEncoding(true); // 외부로 나가는 데이터도 인코딩 설정(응답 시 response)
		return new Filter[] { encodingFilter };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registeration) {
		/* 그 외 기타 사용자 설정 */

		// 사용자 지정 익셉션 설정을 할 것인지 처리
		registeration.setInitParameter("throwExceptionIfNotHandlerFound", "true");
		
		
	}
}
