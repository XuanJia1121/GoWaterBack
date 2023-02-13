package com.xuan.boot.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuan.boot.lab.dto.CustomerDto;
import com.xuan.boot.lab.service.CustomerService;

@Controller
public class BaseController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customer/toLogin")
	public String toLogin() {
		return "login";
	}
	
	@GetMapping("/customer/loginFail")
	public String loginFail(Model model) {
		model.addAttribute("rtn_msg","登入失敗");
		return toLogin();
	}
	
	@RequestMapping("/toHome")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/customer/register")
	public String register(@ModelAttribute("customerDto") CustomerDto customerDto) {
		return "register";
	}
	
	@RequestMapping("/customer/registerAction")
	public String registerAction(@ModelAttribute("customerDto") CustomerDto customerDto,Model model) {
		if (!customerService.isCustomerExist(customerDto.getUsername())) {
			model.addAttribute("rtn_msg","註冊失敗");
			return "register";
		}
		customerService.registerCustomer(customerDto);
		model.addAttribute("rtn_msg","註冊成功");
		return toLogin();
	}
	
}
