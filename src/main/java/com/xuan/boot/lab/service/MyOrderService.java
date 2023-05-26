package com.xuan.boot.lab.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuan.boot.lab.dao.MyOrderDao;
import com.xuan.boot.lab.domain.MyOrder;
import com.xuan.boot.lab.dto.MyOrderDto;

@Service
public class MyOrderService {
	
	@Autowired
	private MyOrderDao orderDao;
	
	public List<MyOrderDto> selectOrderByCid(String cid) {
		return orderDao.selectOrderByid(cid)
				.stream()
				.map(this::transToDto)
				.collect(Collectors.toList());
	}
	
	public void addorder(MyOrderDto orderDto) {
		orderDao.addOrder(transToDomain(orderDto));
	}
	
	private MyOrderDto transToDto(MyOrder order) {
		MyOrderDto orderDto = new MyOrderDto();
		orderDto.setId(order.getId());
		orderDto.setCid(order.getCid());
		orderDto.setCart(order.getCart());
		orderDto.setPrice(order.getPrice());
		return orderDto;
	}
	
	private MyOrder transToDomain(MyOrderDto orderDto) {
		MyOrder order = new MyOrder();
		order.setId(orderDto.getId());
		order.setCid(orderDto.getCid());
		order.setCart(orderDto.getCart());
		order.setPrice(orderDto.getPrice());
		return order;
	}
	
}
