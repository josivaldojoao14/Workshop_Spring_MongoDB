//package com.mongoandspring.workshop_spring_mongo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.mongoandspring.workshop_spring_mongo.services.UserServiceImpl;
//
////@Configuration
//// OLDER VERSION
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{ 
//	
//	@Autowired
//	private  UserServiceImpl userService;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http
//			.httpBasic()
//			.and()
//			.authorizeHttpRequests()
//			.antMatchers(HttpMethod.GET, "/api/users").permitAll()
//			.antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
//			.antMatchers(HttpMethod.POST, "/api/user/save").hasRole("USER")
//			.antMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
//			.anyRequest().authenticated()
//			.and()
//			.csrf().disable();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.userDetailsService(userService)
//			.passwordEncoder(passwordEncoder());
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
// 
//}