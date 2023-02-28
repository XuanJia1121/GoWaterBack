package com.xuan.boot.lab.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderDto implements Serializable {
	
	private static final long serialVersionUID = -3212276149974095485L;
	private Integer id;
	private Integer cid;
	private String details;
	
}
