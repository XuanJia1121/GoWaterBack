package com.xuan.boot.lab.conf;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.xuan.boot.lab.enums.UrlPatten;
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
		//Login
		http.formLogin()
			.loginPage(UrlPatten.CUSTOMER_TOLOGIN.getUrl())
			.loginProcessingUrl("/customer/loginAction")
			.successHandler(baseLoginSuccessService)
			.failureHandler(baseLoginFailService)
			.and()
			.userDetailsService(userDetailAuthService);
		//request setting
		http.authorizeRequests()
			.antMatchers(UrlPatten.unSecurityUrl()).permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable();
		//Oauth2
		http.oauth2Login()
			.loginPage(UrlPatten.CUSTOMER_TOLOGIN.getUrl())
			.successHandler(oauthSuccessService);
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
}
