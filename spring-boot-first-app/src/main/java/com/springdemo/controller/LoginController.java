package com.springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springdemo.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String LoginMessage(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String LoggedInToWelcome(ModelMap model , @RequestParam String name , @RequestParam String password) {
		if(loginService.isValid(name, password)) {
			model.put("name", name);
			return "welcome";
		}
		else {
			model.put("errorMessage", "Invalid User Or Password");
			return "error";
		}
	}
}
