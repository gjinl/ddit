package com.ddit.proj.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler extends 
	SavedRequestAwareAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws ServletException, IOException {
		log.warn("onAuthenticationSuccess!!");
		
		//auth.getPrincipal() : 사용자 정보를 가져옴
		//	시큐리티에서 사용자 정보는 User 클래스의 객체로 저장됨(CustomUser.java 참고)
		User customUser = (User)auth.getPrincipal();
		
		log.info("username : " + customUser.getUsername());
		
		//팁! 로그인 성공 시 나오는 페이지 url설정-관리자, 사용자, 업체, 일반회원에 따라 다르게 처리
		List<String> roleNames = new ArrayList<String>();
		//auth.getAuthorities() : 사용자(1)의 권한(N)정보들
		auth.getAuthorities().forEach(authority->{
			roleNames.add(authority.getAuthority());
		});
		
		log.info("ROLE NAMES : " + roleNames);
		
		//학생(ROLE_STUDENT)
//		if(roleNames.contains("ROLE_STUDENT")) {
//			response.sendRedirect("/student/main");
//		}
		
		//교수(ROLE_PROFESSOR)
//		if(roleNames.contains("ROLE_PROFESSOR")) {
//			response.sendRedirect("/professor/main");
//		}
		
		//관리자(ROLE_ADMIN)
//		if(roleNames.contains("ROLE_ADMIN")) {
//			response.sendRedirect("/admin/main");
//		}
		
		//부모(super)에게 반납
		super.onAuthenticationSuccess(request, response, auth);
	}
}