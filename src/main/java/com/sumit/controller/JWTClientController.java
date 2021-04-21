package com.sumit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sumit.utils.JWTUtility;

@RestController
public class JWTClientController {

	@Autowired
	JWTUtility jWTUtility;
	
	@GetMapping("jwt")
	public String getJwtToken(@RequestHeader("pass-key") String key,@RequestHeader("user-name") String userName)
	{
		return jWTUtility.generateJWToken(key, userName);
	}
	
	@GetMapping("validate")
	public boolean validateJwtToken(@RequestHeader("token") String token)
	{
		return jWTUtility.validateJWToken(token);
	}
}
