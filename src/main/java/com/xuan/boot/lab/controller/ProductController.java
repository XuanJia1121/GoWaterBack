package com.xuan.boot.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xuan.boot.lab.dto.ResponseDto;
import com.xuan.boot.lab.service.ProductService;
import com.xuan.boot.lab.utils.JsonUtil;

@RequestMapping("/api/product")
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/all")
	public ResponseDto allProduct() {
		try {
			return new ResponseDto(ResponseDto.OK,JsonUtil.objectToJson(productService.selectAllProduct()));
		} catch (Exception e) {
			return new ResponseDto(ResponseDto.ERROR,"查詢商品失敗");
		}
	}
	
}
