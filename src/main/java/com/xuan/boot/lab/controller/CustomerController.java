package com.xuan.boot.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
	public CustomerDto hello(@RequestBody CustomerDto customerDto) {
		System.out.println(customerDto);
		return new CustomerDto();
	}
	
}
