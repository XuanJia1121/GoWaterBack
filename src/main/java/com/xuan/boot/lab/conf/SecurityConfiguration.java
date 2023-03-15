package com.xuan.boot.lab.conf;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.xuan.boot.lab.enums.UrlPatten;
import com.xuan.boot.lab.filter.CustomUsernamePasswordAuthenticationFilter;
import com.xuan.boot.lab.filter.MyAuthenticationFilter;
import com.xuan.boot.lab.service.BaseLoginFailService;
import com.xuan.boot.lab.service.BaseLoginSuccessService;
import com.xuan.boot.lab.service.OauthSuccessService;
import com.xuan.boot.lab.service.UserDetailAuthService;

@Configuration
@EnableWebSecurity
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
		//request setting
		http.authorizeRequests()
//			.antMatchers(UrlPatten.unSecurityUrl()).permitAll()
//			.anyRequest().authenticated()
			.anyRequest().permitAll()
			.and()
			.csrf().disable();
		//Oauth2 Google Login
		http.oauth2Login()
			.successHandler(oauthSuccessService);
		//Cros
		http.cors();
		//json valid
		http.addFilterAt(getCustomUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(getMyAuthenticationFilter(), BasicAuthenticationFilter.class);
		//close session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
	
	@Bean 
	public CustomUsernamePasswordAuthenticationFilter getCustomUsernamePasswordAuthenticationFilter() throws Exception {
		CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter(authenticationManagerBean());
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(UrlPatten.CUSTOMER_LOGIN.getUrl(), "POST"));
		filter.setAuthenticationSuccessHandler(baseLoginSuccessService);
		filter.setAuthenticationFailureHandler(baseLoginFailService);
		return filter;
	}
	
	@Bean
	public MyAuthenticationFilter getMyAuthenticationFilter() {
		return new MyAuthenticationFilter();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailAuthService);
		provider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(Collections.singletonList(provider));
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
}
