package com.xuan.boot.lab.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	public final static String JWT_KEY = "MySecretKey";
	public final static String JWT_PREFIX = "Bearer ";
	public final static String HEADER = "Authorization";
	
	public static String genToken(String username) {
		Date expireDate = new Date(System.currentTimeMillis() + 5 * 60 * 1000);
		String token =  Jwts.builder()
				.setSubject(username)
				.setExpiration(expireDate)
				//MySecretKey是自訂的私鑰，HS512是自選的演算法，可以任意改變
				.signWith(SignatureAlgorithm.HS512, JWT_KEY).compact();
		return StringUtils.join(JWT_PREFIX + token);
	}
	
	public static void parseToken(String token) {
		String tokenStr = token.substring(JWT_PREFIX.length());
		Claims claims = Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(tokenStr).getBody();
		System.out.println("Payload:" + claims.toString());
	}
	
}
