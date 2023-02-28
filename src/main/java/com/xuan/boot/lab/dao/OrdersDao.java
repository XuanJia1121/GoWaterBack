package com.xuan.boot.lab.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xuan.boot.lab.domain.Order;

@Mapper
public interface OrdersDao {
	
	Integer selectMaxId();
	
	List<Order> selectById(Integer id);
	
}
