package com.xuan.boot.lab.enums;

import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;


public enum UrlPatten {
	
	//customer
	HELLO("N","/customer/hello"),
	CUSTOMER_LOGIN("N","/customer/loginAction");
	
	private final String isSecur;
	private final String url;
	
	private UrlPatten(String isSecur,String url) {
		this.isSecur = isSecur;
		this.url = url;
	}
	
	public static String[] unSecurityUrl() {
		return Stream.of(UrlPatten.values())
				.filter(t -> StringUtils.equals("N", t.isSecur))
				.map(t -> t.url)
				.toArray(String[]::new);
	}
	
	public String getUrl() {
		return this.url;
	}
	
}
