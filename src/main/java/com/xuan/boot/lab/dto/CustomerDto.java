package com.xuan.boot.lab.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
	
	private String cid;
	private String username;
	private String password;
	private String email;
	
}
