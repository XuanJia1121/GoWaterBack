package com.xuan.boot.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xuan.boot.lab.dao.CustomerDao;
import com.xuan.boot.lab.domain.Customer;
import com.xuan.boot.lab.dto.CustomerDto;
import com.xuan.boot.lab.encode.PasswordEncoder;

/**
 * @author xuan
 *
 */

@Component
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public boolean isCustomerExist(String name) {
		return selectByUsername(name) == null;
	}
	
	public Customer selectByUsername(String name) {
		return customerDao.selectByUsername(name);
	}
	
	@Transactional
	public void registerCustomer(CustomerDto customerDto) {
		Customer customer = Customer
				.builder()
				.username(customerDto.getUsername())
				.password(PasswordEncoder.encodeByBcrypt(customerDto.getPassword()))
				.email(customerDto.getEmail()).build();
		customerDao.insertCustomer(customer);
	}
	
}
