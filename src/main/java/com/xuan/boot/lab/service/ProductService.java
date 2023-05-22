package com.xuan.boot.lab.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuan.boot.lab.dao.ProductDao;
import com.xuan.boot.lab.dto.ProductDto;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao; 
	
	public List<ProductDto> selectAllProduct(){
		return productDao.selectAllProduct().stream().map(t -> {
			return ProductDto
					.builder()
					.pid(t.getPid())
					.pname(t.getPname())
					.description(t.getDescription())
					.price(t.getPrice())
					.build();
		}).collect(Collectors.toList());
	}
	
}
