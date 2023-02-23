package com.xuan.boot.lab.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product implements Serializable {
	
	private static final long serialVersionUID = 300131315085133630L;
	private Integer id;
	private String name;
	private String description;
	private BigDecimal price;
	
}
