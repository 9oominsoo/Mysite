package com.josh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.josh.dto.UserDto;

// HandleMethodArgumentResolver : 파라미터를 바인딩 해주는 인터페이스
public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		//parameter에 authuser가 붙어있는지 확인하는 메소드
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		// = AuthUser authUser = new AuthUser()와 같은 의미
		
		//@AuthUser가 안 붙어 있으면 
		if(authUser == null) {
			return false;
		}
		
		//앞으로 쓸 파라미터가 UserDto가 아니면 
		if(parameter.getParameterType().equals(UserDto.class) == false) {
			return false;
		}
		
		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// 올바른 형태에 authuser가 붙어있는지 확인하는 메소드
		
		if(supportsParameter(parameter) == false) {
			//우리가 원하는 형태가 아니다. 쓸수 잇는 파라미터가 아니다, @AuthUser(X) UserDto(X) 모양이 아니다.
			return WebArgumentResolver.UNRESOLVED;
		}
			
		//@AuthUser(O) UserDto(O)
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null) { //@AuthUser(O), UserDto(O), 세션(X)  
			return WebArgumentResolver.UNRESOLVED;
		}
			
		//@AuthUser(O), UserDto(O), 세션(O)
		return session.getAttribute("authUser");
			
	}
	
}
