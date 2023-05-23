package com.xuan.boot.lab.dto;

import lombok.Data;

@Data
public class ResponseDto {
	
	public static String OK = "200";
	public static String BAD_REQ = "400";
	public static String ERROR = "500";
	
	private String statuscode;
	private String value;
	
	public ResponseDto(String statuscode,String json) {
		this.statuscode = statuscode;
		this.value = json;
	}
	
}
