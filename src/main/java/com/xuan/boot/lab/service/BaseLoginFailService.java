package com.xuan.boot.lab.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.xuan.boot.lab.dto.ResponseDto;
import com.xuan.boot.lab.utils.ResponseUtil;

@Component
public class BaseLoginFailService implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		ResponseUtil.response(response,new ResponseDto("400","帳號或密碼錯誤"));
	}

}
