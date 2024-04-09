package com.example.healthApp.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	  public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629"; 
	   
	public String createToken(String username)
	{
		Map<String, Object> claims = new HashMap<>(); 
		 
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
				.signWith(getSignKey())
				.compact();
				
	}

    private Key getSignKey() { 
        byte[] keyBytes= Decoders.BASE64.decode(SECRET); 
        return Keys.hmacShaKeyFor(keyBytes); 
    } 
  
	public Claims extractClaims(String token)
	{
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean isTokenExpired(String token)
	{
		Claims claims = extractClaims(token);
		Date expirationDate = claims.getExpiration();
		return ((new Date()).after(expirationDate));
	}
	
	public String getUsernameFromToken(String token)
	{
		Claims claims = extractClaims(token);

		return claims.getSubject();
	}
	
	public boolean isValidToken(UserDetails userDetails, String token)
	{
		String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
