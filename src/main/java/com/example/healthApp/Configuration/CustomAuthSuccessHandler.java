package com.example.healthApp.Configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.healthApp.Service.JwtService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler{
	@Autowired
	private JwtService jwtService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
	
		String token = jwtService.createToken(authentication.getName());
		// Add token to the response headers or body as needed
        response.setHeader("Authorization", "Bearer " + token);
        response.getWriter().write("Authentication successful. Token generated: " + token);
        response.setStatus(HttpServletResponse.SC_OK);
        
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Admin")))
		{
			response.sendRedirect("/healthApp/adminDashboard");
		}
		else
		{
			response.sendRedirect("/healthApp/userDashboard");
		}
	}

}
