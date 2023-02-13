package com.xuan.boot.lab.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.xuan.boot.lab.utils.JwtUtil;

@Component
public class BaseLoginSuccessService implements AuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(); 

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();
		Cookie cookie = new Cookie("MyToken", JwtUtil.genToken(user.getUsername()));
		response.addCookie(cookie);
		redirectStrategy.sendRedirect(request, response, "/toHome");
	}
	
}