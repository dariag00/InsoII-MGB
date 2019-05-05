package com.unileon.insoII.mgb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unileon.insoII.mgb.form.model.UserForm;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.service.LoginService;
import com.unileon.insoII.mgb.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value= {"/login", "/"}, method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value={"/login", "/"}, method = RequestMethod.POST)
	public String showMainPage(ModelMap model, @RequestParam String email, @RequestParam String password) {
		boolean isValidUser = loginService.logIn(email, password);
		if(isValidUser) {
			return "redirect:welcome";
		}else {
			model.addAttribute("errorMessage", "The user doesn't exist or the password do not match");
			return "login";
		}
	}
	
	@RequestMapping(value={"/create_account"}, method = RequestMethod.GET)
	public String showCreateAccountPage(Model model) {
		model.addAttribute("user", new UserForm());
		return "create_account";
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public String createUser(@ModelAttribute("user") UserForm userForm, BindingResult bindingResult) {
		
		if (!bindingResult.hasErrors()) {
			if(!userService.createUser(userForm)) {
				//ERROR
				return "redirect:login";
			}
			return "redirect:login";
		}
		
		return "redirect:login";
	}
	

}
