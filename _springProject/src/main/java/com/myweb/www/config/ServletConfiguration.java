package com.myweb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = { "com.myweb.www.controller", "com.myweb.www.handler" })
public class ServletConfiguration implements WebMvcConfigurer {
//jsp라인에 대한 설정

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

		// 파일 업로드 경로
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///D:\\_myweb\\_java\\fileupload\\");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class); // jstl 사용할 수 있도록 설정 (<c:foreach> 같은거)
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver); // 설정한 정보 담기

	}

	// bean으로 multipartResolver 설정
	//보통 메서드 이름으로 등록이 되는데(getMultipartResolver) 이렇게 직접 bean 이름을 설정할 수 있음
	@Bean(name="multipartResolver") 
	public MultipartResolver getMultipartResolver() {
		StandardServletMultipartResolver  multipartResolver = 
				new StandardServletMultipartResolver();
		
		return multipartResolver;
	}

}
