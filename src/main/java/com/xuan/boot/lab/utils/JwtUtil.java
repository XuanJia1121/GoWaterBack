package com.xuan.boot.lab.utils;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	public final static String JWT_KEY = "MySecret";

	public static String genToken(String username) {
		Date expireDate = new Date(System.currentTimeMillis() + 5 * 60 * 1000);
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(expireDate)
				// MySecret是自訂的私鑰，HS512是自選的演算法，可以任意改變
				.signWith(SignatureAlgorithm.HS512, JWT_KEY).compact();
	}
}
