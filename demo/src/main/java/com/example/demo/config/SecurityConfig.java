package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.rest.CustomAccessDeniedHandler;
import com.example.demo.rest.JwtAuthenticationTokenFilter;
import com.example.demo.rest.RestAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
		JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
		jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
		return jwtAuthenticationTokenFilter;
	}

	@Bean
	public RestAuthenticationEntryPoint restServicesEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	protected void configure(HttpSecurity http) throws Exception {
		// Disable crsf cho đường dẫn /rest/**.disable()
//		http.csrf().disable();
		http.csrf().ignoringAntMatchers("/api/**");
		http.authorizeRequests().antMatchers("/api/login**").permitAll();
		http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/**").access("hasRole('ADMIN') or hasRole('USER')")
				.antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ADMIN')")
				.antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ADMIN')").and()
				.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
		
		
		
	}
}
