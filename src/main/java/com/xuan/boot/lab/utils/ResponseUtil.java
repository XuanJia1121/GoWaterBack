package com.xuan.boot.lab.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	
	public static void response(HttpServletResponse response,int status,String jsonStr) throws IOException {
		response.setStatus(status);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(jsonStr);
	}
	
}
