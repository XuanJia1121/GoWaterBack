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

import com.xuan.boot.lab.service.BaseFailService;
import com.xuan.boot.lab.service.BaseSuccessService;
import com.xuan.boot.lab.service.OauthSuccessService;
import com.xuan.boot.lab.service.UserDetailAuthService;

@Configuration
public class SecurityConfiguration {

	@Autowired
	UserDetailAuthService userDetailAuthService;
	@Autowired
	OauthSuccessService oauthSuccessService;
	@Autowired
	BaseSuccessService baseSuccessService;
	@Autowired
	BaseFailService baseFailService;
	
	@Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINESE);
        return localeResolver;
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//Login
		http.formLogin()
			.loginPage("/customer/toLogin")
			.loginProcessingUrl("/customer/loginAction")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(baseSuccessService)
			.failureHandler(baseFailService)
			.and()
			.userDetailsService(userDetailAuthService);
		//request setting
		http.authorizeRequests()
			.antMatchers("/customer/toLogin","/customer/register","/customer/registerAction").permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable();
		//Oauth2
		http.oauth2Login()
			.loginPage("/customer/toLogin")
			.successHandler(oauthSuccessService);
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
}
