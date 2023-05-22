package com.xuan.boot.lab.conf;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.xuan.boot.lab.filter.JsonFormLoginAuthenticationFilter;
import com.xuan.boot.lab.filter.JwtAuthenticationFilter;
import com.xuan.boot.lab.service.BaseLoginFailService;
import com.xuan.boot.lab.service.BaseLoginSuccessService;
import com.xuan.boot.lab.service.UserDetailAuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserDetailAuthService userDetailAuthService;
	@Autowired
	private BaseLoginSuccessService baseLoginSuccessService;
	@Autowired
	private BaseLoginFailService baseLoginFailService;
	 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//request setting
		http.authorizeRequests()
			.anyRequest().permitAll()
			.and()
			.csrf().disable();
		//Cros
		http.cors();
		//Json valid
		http.addFilterAt(jsonFormLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtAuthenticationFilter(), BasicAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean 
	public JsonFormLoginAuthenticationFilter jsonFormLoginAuthenticationFilter() throws Exception {
		JsonFormLoginAuthenticationFilter filter = new JsonFormLoginAuthenticationFilter(authenticationManagerBean());
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(JsonFormLoginAuthenticationFilter.LOGIN_URL, HttpMethod.POST.toString()));
		filter.setAuthenticationSuccessHandler(baseLoginSuccessService);
		filter.setAuthenticationFailureHandler(baseLoginFailService);
		return filter;
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
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
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        //the below three lines will add the relevant CORS response headers
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
}
