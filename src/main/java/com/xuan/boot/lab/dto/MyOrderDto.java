package com.xuan.boot.lab.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MyOrderDto implements Serializable {
	
	private static final long serialVersionUID = -3212276149974095485L;
	private Long id;
	private String cid;
	private String cart;
	private Integer price;
	
}
