package com.xuan.boot.lab.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersDao {
	
	Integer selectMaxId();
	
}
