package com.xuan.boot.lab.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.xuan.boot.lab.dto.ResponseDto;
import com.xuan.boot.lab.utils.JwtUtil;
import com.xuan.boot.lab.utils.ResponseUtil;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
		if (!StringUtils.equals(req.getMethod(), HttpMethod.GET.toString()) && !StringUtils.equals(JsonFormLoginAuthenticationFilter.LOGIN_URL, req.getServletPath())) {
			String authorHeader = req.getHeader(JwtUtil.HEADER);
			//以jjwt驗證token，只要驗證成功就放行
			//驗證失敗會拋exception，直接將錯誤訊息傳回
			if (authorHeader != null && authorHeader.startsWith(JwtUtil.JWT_PREFIX)) {
				try {
					String token = authorHeader.substring(JwtUtil.JWT_PREFIX.length());
					JwtUtil.parseToken(token);
					chain.doFilter(req, res);
				} catch (Exception e) {
					ResponseUtil.response(res, new ResponseDto(ResponseDto.BAD_REQ,"Not Valid Token"));
				}
			} else {
				ResponseUtil.response(res, new ResponseDto(ResponseDto.BAD_REQ,"Not Valid Token"));
			}
		} else {
			chain.doFilter(req, res);
		}
	}
}
