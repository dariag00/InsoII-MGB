package com.unileon.insoII.mgb.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unileon.insoII.mgb.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	
	@RequestMapping(value= {"/login", "/"}, method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value={"/login", "/"}, method = RequestMethod.POST)
	public String showMainPage(ModelMap model, @RequestParam String email, @RequestParam String password) {
		boolean isValidUser = loginService.logIn(email, password);
		if(isValidUser) {
			return "welcome";
		}else {
			model.addAttribute("errorMessage", "The user doesn't exist or the password do not match");
			return "login";
		}
	}
	
	@RequestMapping(value={"/create_account"}, method = RequestMethod.GET)
	public String showCreateAccountPage(ModelMap model) {
		return "create_account";
	}
	

}
