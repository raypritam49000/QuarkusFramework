package com.rest.api.utils;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
	
	public Key generateKey() {
		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		return key;
	}
	
	public String generateTokenFromUsername(String username) {
		Key key = generateKey();
		String jwtToken = Jwts.builder().setSubject(username).setIssuer(username).setIssuedAt(new Date())
				.setExpiration(toDate(LocalDateTime.now().plusMinutes(15L))).signWith(SignatureAlgorithm.HS512, key)
				.compact();
		return jwtToken;

	}
	
	
	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	
}
