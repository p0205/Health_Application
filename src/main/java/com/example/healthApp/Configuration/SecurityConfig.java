package com.example.healthApp.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.healthApp.Service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private CustomAuthSuccessHandler customSuccessHandler;
	
	@Autowired
	private CustomDeniedAccess customDeniedAccess;
	
	@Autowired
	private CustomAuthFailureHandler customAuthFailureHandler;
	
	@Autowired
	private JwtAuthTokenFilter jwtAuthTokenFilter;
	
	@Bean(name="pwencoder")
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(myUserDetailsService);
		return provider;
	}
	
	@Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	        return authConfig.getAuthenticationManager();
    }
	
	
	@Bean
	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
	    http
	    .authorizeHttpRequests(auth -> 
			auth.requestMatchers("healthApp/login").permitAll()
				.requestMatchers("healthApp/logout").permitAll()
				.requestMatchers("healthApp/deniedAccess").permitAll()
				.requestMatchers("healthApp/failedAuth").permitAll()
				.requestMatchers("healthApp/generateToken").permitAll()
				.requestMatchers("healthApp/admin/**").hasRole("Admin")
				.anyRequest().authenticated()
	    )
	    
	     
//    	.formLogin(formLogin -> formLogin.successHandler(customSuccessHandler).failureHandler(customAuthFailureHandler)) // Set the URL to redirect after successful login) // Enable form login
    	.sessionManagement() 
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class)
    	.logout(logout->logout.logoutSuccessUrl("/healthapp/logout"))
    	
	    .exceptionHandling(deniedAccess->deniedAccess.accessDeniedHandler(customDeniedAccess))
        .csrf((csrf) -> csrf.disable());
       
	    
	    //if don't have this line, the Spring Security will use default provider
	    http.authenticationProvider(authProvider());
	    http.httpBasic();
        
	    return http.build();
	}

	
	
}
