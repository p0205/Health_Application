package com.example.healthApp.Configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.healthApp.Service.JwtService;
import com.example.healthApp.Service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter{

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//check if the request use jwt token
		String authHeader = null;
		String token = null;
		
		authHeader = request.getHeader("Authorization");
		
				
		if(authHeader!=null&&authHeader.startsWith("Bearer"))
		{
			token = authHeader.substring(7);
		}
	
		
		if(token!=null)
		{
			
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtService.getUsernameFromToken(token));
		
			if(jwtService.isValidToken(userDetails, token))
			{
				
				//set authenticated status to true
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
				//set the context to (already) authenticated 
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
		
//		
	}

}
