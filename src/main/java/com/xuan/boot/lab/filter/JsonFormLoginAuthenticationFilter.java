package com.xuan.boot.lab.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public static final String LOGIN_URL = "/api/customer/login";
	
	private AuthenticationManager authenticationManager;

	public JsonFormLoginAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		//只接受Post請求
		if (!StringUtils.equals(request.getMethod(),HttpMethod.POST.toString()))
			throw new AuthenticationServiceException("Authentication method not supported" + request.getMethod());
		//確認是JSON格式
		if (StringUtils.equals(MediaType.APPLICATION_JSON_VALUE,request.getContentType())) {
			String username = StringUtils.EMPTY;
			String password = StringUtils.EMPTY;
			try {
				Map<String,String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
				username = map.get("username");
				password = map.get("password");
			} catch (IOException e) {
				e.printStackTrace();
			}
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(StringUtils.trim(username),StringUtils.trim(password));
			setDetails(request, authRequest);
			return this.getAuthenticationManager().authenticate(authRequest);
		}
		return super.attemptAuthentication(request, response);
	}

}
