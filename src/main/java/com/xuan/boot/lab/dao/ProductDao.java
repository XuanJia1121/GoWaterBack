package com.xuan.boot.lab.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xuan.boot.lab.domain.Product;

@Mapper
public interface ProductDao {
	
	public List<Product> selectAllProduct();
	
}
