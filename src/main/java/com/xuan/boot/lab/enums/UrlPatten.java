package com.xuan.boot.lab.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;


public enum UrlPatten {
	
	//customer
	HELLO("N","/customer/hello"),
	CUSTOMER_LOGIN("N","/customer/loginAction"),
	GOOGLE_OAUTH2("N","/login/oauth2/code/google"),
	
	//product 
	ALL_PRODUCT("N","/product/all"),
	
	//order
	ALL_ORDERS("N","/orders/allOrders");
	
	
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
	
	public static List<String> unSecurityUrlList() {
		return Stream.of(UrlPatten.values())
				.filter(t -> StringUtils.equals("N", t.isSecur))
				.map(t -> t.url)
				.collect(Collectors.toList());
	}
	
	public String getUrl() {
		return this.url;
	}
	
}
