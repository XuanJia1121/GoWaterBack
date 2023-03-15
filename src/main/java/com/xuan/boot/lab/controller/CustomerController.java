package com.xuan.boot.lab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xuan.boot.lab.dto.CustomerDto;
import com.xuan.boot.lab.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping("/hello")
	public CustomerDto hello(HttpServletRequest req,HttpServletResponse res) {
		return new CustomerDto();
	}
	
}
