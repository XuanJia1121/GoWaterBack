package com.xuan.boot.lab.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.xuan.boot.lab.domain.Customer;
import com.xuan.boot.lab.dto.CustomerDto;
import com.xuan.boot.lab.utils.JsonUtil;
import com.xuan.boot.lab.utils.JwtUtil;
import com.xuan.boot.lab.utils.ResponseUtil;

@Component
public class BaseLoginSuccessService implements AuthenticationSuccessHandler {
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();
		String token = JwtUtil.genToken(user.getUsername());
		Customer customer = customerService.selectByUsername(user.getUsername());
		//Trans To Dto
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCid(customer.getCid());
		customerDto.setUsername(customer.getUsername());
		customerDto.setEmail(customer.getEmail());
		customerDto.setToken(token);
		//return
		ResponseUtil.response(response, HttpServletResponse.SC_OK, JsonUtil.objectToJson(customerDto));
	}
	
}
