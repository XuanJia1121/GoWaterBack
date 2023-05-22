package com.xuan.boot.lab.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto implements Serializable {
	
	private static final long serialVersionUID = 4992313249461999184L;
	private Integer pid;
	private String pname;
	private String description;
	private Integer price;

}
