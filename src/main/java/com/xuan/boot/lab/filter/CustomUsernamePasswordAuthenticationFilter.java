package com.xuan.boot.lab.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		//只接受Post請求
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported" + request.getMethod());
		}
		// 確認是JSON格式
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			String username = null;
			String password = null;
			try {
				Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
				username = map.get("username");
				password = map.get("password");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (StringUtils.isBlank(username)) {
				throw new AuthenticationServiceException("username 錯誤!");
			}
			if (StringUtils.isBlank(password)) {
				throw new AuthenticationServiceException("password 錯誤!");
			}
			username = username.trim();
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password);
			setDetails(request, authRequest);
			return this.getAuthenticationManager().authenticate(authRequest);
		}
		return super.attemptAuthentication(request, response);
	}

}
