package com.xuan.boot.lab.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuan.boot.lab.dao.OrdersDao;
import com.xuan.boot.lab.dto.CustomerDto;
import com.xuan.boot.lab.dto.OrderDto;

@Service
public class OrderService {
	
	@Autowired
	private OrdersDao ordersDao;
	
	public Integer selectMaxId() {
		return Optional.ofNullable(ordersDao.selectMaxId()).orElse(0);
	}
	
	public List<OrderDto> selectById(Integer id){
		return ordersDao.selectById(id).stream().map(t -> {
			OrderDto orderDto = new OrderDto();
			orderDto.setId(t.getId());
			orderDto.setCid(t.getCid());
			orderDto.setDetails(t.getDetail());
			return orderDto;
		}).collect(Collectors.toList());
	}
	
	public void insertOrder(CustomerDto customerDto) {
		
	}
	
}
