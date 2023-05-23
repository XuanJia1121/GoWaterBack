package com.xuan.boot.lab.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.xuan.boot.lab.dto.ResponseDto;

public class ResponseUtil {
	
	public static void response(HttpServletResponse response,ResponseDto responseDto) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(JsonUtil.objectToJson(responseDto));
	}
	
}
