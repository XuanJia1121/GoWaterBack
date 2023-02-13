package com.xuan.boot.lab.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xuan.boot.lab.domain.Customer;

@Mapper
public interface CustomerDao {
	
	Customer selectByUsername(String username);
	
	void insertCustomer(Customer customer);
	
}
