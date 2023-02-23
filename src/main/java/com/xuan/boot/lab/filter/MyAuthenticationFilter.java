package com.xuan.boot.lab.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.xuan.boot.lab.enums.UrlPatten;
import com.xuan.boot.lab.utils.JwtUtil;
import com.xuan.boot.lab.utils.ResponseUtil;

public class MyAuthenticationFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
		if (!UrlPatten.unSecurityUrlList().stream().anyMatch(t -> StringUtils.equals(req.getServletPath(),t))) {
			String authorHeader = req.getHeader(JwtUtil.HEADER);
			String bearer = "Bearer ";
			// 以jjwt驗證token，只要驗證成功就放行
			// 驗證失敗會拋exception，直接將錯誤訊息傳回
			if (authorHeader != null && authorHeader.startsWith(bearer)) {
				try {
					String token = authorHeader.substring(bearer.length());
					JwtUtil.parseToken(token);
					chain.doFilter(req, res);
				} catch (Exception e) {
					ResponseUtil.response(res, HttpServletResponse.SC_BAD_REQUEST, "Not Valid Token");
				}
			} else {
				ResponseUtil.response(res, HttpServletResponse.SC_BAD_REQUEST, "Not Valid Token");
			}
		} else {
			chain.doFilter(req, res);
		}
	}
}
