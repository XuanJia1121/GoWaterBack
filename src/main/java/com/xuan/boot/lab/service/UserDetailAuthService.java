package com.xuan.boot.lab.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.xuan.boot.lab.domain.Customer;

@Component
public class UserDetailAuthService implements UserDetailsService {
	
	@Autowired
	CustomerService customerService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerService.selectByUsername(username);
		if (customer == null) {
			throw new UsernameNotFoundException("");
		}
		return new User(customer.getUsername(), customer.getPassword(), Collections.emptyList());
	}
	
}
