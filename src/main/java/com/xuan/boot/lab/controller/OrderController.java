package com.xuan.boot.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xuan.boot.lab.dto.CustomerDto;
import com.xuan.boot.lab.dto.OrderDto;
import com.xuan.boot.lab.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/allOrders")
	public List<OrderDto> getOrdersById(@RequestBody CustomerDto customerDto) {
		return orderService.selectById(Integer.valueOf(customerDto.getCid()));
	}

	@RequestMapping("/addOrder")
	public ResponseEntity<String> addOrder(@RequestBody OrderDto orderDto) {
		try {
			orderService.insertOrder(orderDto);
			return ResponseEntity.ok("訂單成功");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("訂單失敗");
		}
	}

}
