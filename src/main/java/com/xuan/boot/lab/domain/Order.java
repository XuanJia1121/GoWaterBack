package com.xuan.boot.lab.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Order implements Serializable {
	
	private static final long serialVersionUID = -3212276149974095485L;
	private Integer id;
	private Integer cid;
	private Integer pid;
	private Integer count;
	private BigDecimal price;
	
}
