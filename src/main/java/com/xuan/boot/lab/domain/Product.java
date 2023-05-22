package com.xuan.boot.lab.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Product implements Serializable {
	
	private static final long serialVersionUID = 300131315085133630L;
	private Integer pid;
	private String pname;
	private String description;
	private Integer price;
	
}
