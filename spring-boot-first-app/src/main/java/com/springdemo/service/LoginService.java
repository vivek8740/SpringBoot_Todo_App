package com.springdemo.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	
	public boolean isValid(String userid,String password) {
		//Valisd user : Oracle/NextGen
		return userid.equalsIgnoreCase("Oracle") && password.equalsIgnoreCase("NextGen");
	}

}
