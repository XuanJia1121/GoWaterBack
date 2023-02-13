package com.xuan.boot.lab.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Customer implements Serializable { 
	
	private static final long serialVersionUID = -2961937847997736384L;
	private String cid;
	private String username;
	private String password;
	private String email;
	
}
