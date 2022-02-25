package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.config.AppProperties;
import com.example.demo.model.UserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;

@Service
public class TokenService {

		@Autowired
	    private AppProperties appProperties;

	    public String createToken(Authentication authentication) {
	        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

	        Date now = new Date();
	        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

	        return Jwts.builder()
	                .setSubject(Long.toString(userPrincipal.getId()))
	                .setIssuedAt(new Date())
	                .setExpiration(expiryDate)
	                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
	                .compact();
	    }

	    public Long getUserIdFromToken(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey(appProperties.getAuth().getTokenSecret())
	                .parseClaimsJws(token)
	                .getBody();

	        return Long.parseLong(claims.getSubject());
	    }

		public boolean validateToken(String authToken) {
			try {
				Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
				return true;
			} catch (SignatureException ex) {
				return false;
			}
		}
}
