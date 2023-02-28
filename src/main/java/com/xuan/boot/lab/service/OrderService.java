package com.xuan.boot.lab.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuan.boot.lab.dao.OrdersDao;

@Service
public class OrderService {
	
	@Autowired
	private OrdersDao ordersDao;
	
	public Integer selectMaxId() {
		return Optional.ofNullable(ordersDao.selectMaxId()).orElse(0);
	}
}
