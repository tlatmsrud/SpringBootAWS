package com.ssk.book.springboot.config.auth;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ssk.book.springboot.config.auth.dto.SessionUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver{
	
	private final HttpSession httpSession;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
		boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
		return isLoginUserAnnotation && isUserClass;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// TODO Auto-generated method stub
		return httpSession.getAttribute("user");
	}
}
