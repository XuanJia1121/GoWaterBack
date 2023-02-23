package com.xuan.boot.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xuan.boot.lab.dto.ProductDto;
import com.xuan.boot.lab.service.ProductService;

@RequestMapping("/product")
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDto> allProduct() {
		return productService.selectAllProduct();
	}
	
}
