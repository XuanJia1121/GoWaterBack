package com.xuan.boot.lab.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto implements Serializable {
	
	private static final long serialVersionUID = 4707778276917441795L;
	private String cid;
	private String username;
	private String password;
	private String email;
	private String token;
}
