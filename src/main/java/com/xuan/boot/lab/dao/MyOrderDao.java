package com.xuan.boot.lab.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xuan.boot.lab.domain.MyOrder;

@Mapper
public interface MyOrderDao {
	
	public List<MyOrder> selectOrderByid(String cid);
	
	public void addOrder(MyOrder order);
	
}
