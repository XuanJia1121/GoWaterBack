package com.xuan.boot.lab.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.xuan.boot.lab.enums.UrlPatten;
import com.xuan.boot.lab.filter.CustomUsernamePasswordAuthenticationFilter;
import com.xuan.boot.lab.service.BaseLoginFailService;
import com.xuan.boot.lab.service.BaseLoginSuccessService;
import com.xuan.boot.lab.service.OauthSuccessService;
import com.xuan.boot.lab.service.UserDetailAuthService;

@Configuration
public class SecurityConfiguration {

	@Autowired
	UserDetailAuthService userDetailAuthService;
	@Autowired
	OauthSuccessService oauthSuccessService;
	@Autowired
	BaseLoginSuccessService baseLoginSuccessService;
	@Autowired
	BaseLoginFailService baseLoginFailService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//Base Login
		http.formLogin()
			.loginPage(UrlPatten.CUSTOMER_TOLOGIN.getUrl())
			.loginProcessingUrl("/customer/loginAction")
			.successHandler(baseLoginSuccessService)
			.failureHandler(baseLoginFailService)
			.and()
			.userDetailsService(userDetailAuthService);
		//request setting
		http.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.antMatchers(UrlPatten.unSecurityUrl()).permitAll()
			.anyRequest().authenticated()
			.and()
			.csrf().disable();
		//Oauth2 Google Login
		http.oauth2Login()
			.successHandler(oauthSuccessService);
		//Cros
		http.cors();
		//json valid
		http.addFilterAt(getCustomUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean 
	public CustomUsernamePasswordAuthenticationFilter getCustomUsernamePasswordAuthenticationFilter() {
		return new CustomUsernamePasswordAuthenticationFilter();
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
}
