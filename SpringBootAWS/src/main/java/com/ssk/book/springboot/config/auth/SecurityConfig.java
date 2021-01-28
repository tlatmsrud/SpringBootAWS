package com.ssk.book.springboot.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ssk.book.springboot.domain.user.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final로 선언된 멤버필드를 받는 생성자 자동 생성함. 이를 사용하는 이유는 스프링 설정파일에서 DI를 해주기 위함.
@EnableWebSecurity //Spring Security 설정을 활성화 시키기 위한 옵션.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final CustomOAuth2UserService customOAuth2UserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable() 
			.headers().frameOptions().disable()
		.and()
			.authorizeRequests()
			.antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
			.antMatchers("/api/v1/**").hasRole(Role.USER.name())
			.anyRequest().authenticated()
		.and()
			.logout()
				.logoutSuccessUrl("/")
		.and()
			.oauth2Login()
				.userInfoEndpoint()
					.userService(customOAuth2UserService);
	}
	
	
}

