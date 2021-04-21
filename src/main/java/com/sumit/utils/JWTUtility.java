package com.sumit.utils;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtility {

	public static final long JWT_TOKEN_VALIDITY = 60;
	public String generateJWToken(String secret,String subject)
	{
		//secret.getBytes()
		//byte[] encode = Base64.getEncoder().encode(secret.getBytes());
		/*
			The above encoding or byte[] is needed before we supply the secret to sign the token. Without encoding it
			or without supplying it as a byte array, the symmetric or shared key 
			we are using at the time of generating and validating the token, will not work properly.
			It was observed that without encoding it, any secret String is validating the token.
			To ensure more, see the below method signatures from Jwts class.
			1. JwtBuilder signWith(SignatureAlgorithm alg, byte[] secretKey);
			2. JwtBuilder signWith(SignatureAlgorithm alg, String base64EncodedSecretKey);
			3. JwtBuilder signWith(SignatureAlgorithm alg, Key key);
		*/
		
		return Jwts.builder().setClaims(new HashMap<>()).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
		.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}
	
	public boolean validateJWToken(String token)
	{
		String secret = "sample1";
//		byte[] encode = Base64.getEncoder().encode(secret.getBytes());
		Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		String subject = claims.getSubject();
		return subject.equals("Sumit");
	}
}
