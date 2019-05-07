package com.josh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.josh.dto.UserDto;
import com.josh.service.UserService;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private UserService userService;
	
	@Override
	// 애는 파라미터가 이미 정해져 있기 때문에 spring에서 처럼 프레임 워크가 파라미터를 받아주지 못한다 .
	// 따라서 servlet/jsp 방식 처럼 get/setter를 사용해서 파라미터를 직접 꺼내 써야 한다.
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("loginInterceptor()");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDto userDto = userService.login(email, password);
		
		if(userDto == null) {
			//boolean 형으로만 반환할 수 있기 떼문
			response.sendRedirect(request.getContextPath()+"/user/loginform");
			return false;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("authUser", userDto);
		response.sendRedirect(request.getContextPath()+"/main");
		//return true를 하면 controller로 가므로, false를 return해야 컨트롤러로 가지 않고 redirect만 하고 끝나게 된다.
		return false;
	}
	

}
