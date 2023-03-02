package com.xuan.boot.lab.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuan.boot.lab.dao.OrdersDao;
import com.xuan.boot.lab.domain.Order;
import com.xuan.boot.lab.dto.OrderDto;

@Service
public class OrderService {
	
	@Autowired
	private OrdersDao ordersDao;
	
	private Integer selectMaxId() {
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
	
	public void insertOrder(OrderDto orderDto) {
		Order order = new Order();
		order.setId(selectMaxId() + 1);
		order.setCid(orderDto.getCid());
		order.setDetail(orderDto.getDetails());
		ordersDao.insertOrder(order);
	}
	
}
