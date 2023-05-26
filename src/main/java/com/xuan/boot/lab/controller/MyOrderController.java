package com.xuan.boot.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xuan.boot.lab.dto.MyOrderDto;
import com.xuan.boot.lab.dto.ResponseDto;
import com.xuan.boot.lab.service.MyOrderService;
import com.xuan.boot.lab.utils.JsonUtil;

@RequestMapping("/api/order")
@RestController
public class MyOrderController {
	
	@Autowired
	private MyOrderService orderService;
	
	@RequestMapping(value = "/orders")
	public ResponseDto getOrderByCid(@RequestBody String cid) {
		try {
			System.out.println(cid);
			List<MyOrderDto> list = orderService.selectOrderByCid(cid);
			return new ResponseDto(ResponseDto.OK,JsonUtil.objectToJson(list));
		} catch (Exception e) {
			return new ResponseDto(ResponseDto.ERROR,"訂單查詢失敗");
		}
	}
	
	@RequestMapping(value = "/add")
	public ResponseDto getOrderByCid(@RequestBody MyOrderDto orderDto) {
		try {
			orderService.addorder(orderDto);
			return new ResponseDto(ResponseDto.OK,"訂單新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDto(ResponseDto.ERROR,"訂單新增失敗");
		}
	}
	
	
}
