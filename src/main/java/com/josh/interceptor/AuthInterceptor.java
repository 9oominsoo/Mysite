package com.josh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.josh.dto.UserDto;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("*****************AuthInterceptor*****************");
        //1. handler 종류 확인 	
			//controller에 있는 메소드 인지 체크 
		if(handler instanceof HandlerMethod == false) {
			System.out.println("controller 에 있는 메소드가 아닙니다");
			return true;
		}
        
		//2.@Auth 가 있는지?
		Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
		if(auth == null) {
			System.out.println("@Auth 없음");
			return true;
		}
		
        //3.@Auth 가 있으면  session 있는지 체크
		HttpSession session = request.getSession();
		if(session == null) {
			System.out.println("세션이 없어서 login form으로 redirect 시킵니다");
			System.out.println("@Authuser 잇음 session 없음");
			
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
        //4.@Auth 가 있고 session 이 있고 session에 authUser가 있는지?
		UserDto authUser = (UserDto)session.getAttribute("authUser");
		if(authUser == null) {
			System.out.println("로그인을 하지 않았습니다.");

			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}
        //5.모든 조건을 만족한 사용자는 요청한 곳으로 보낸다.
		System.out.println("@Auth있음, session있음, authUser있음");
        return true; 

	}

	
}
