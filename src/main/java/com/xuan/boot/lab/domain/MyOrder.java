package com.xuan.boot.lab.domain;

import lombok.Data;

@Data
public class MyOrder {
	
	private Long id;
	private String cid;
	private String cart;
	private Integer price;
	
}
