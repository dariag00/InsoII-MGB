package com.unileon.insoII.mgb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.service.LoginService;
import com.unileon.insoII.mgb.service.UserService;

@Controller
public class WelcomeController {
	
	@Autowired
	LoginService loginService;
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value= {"/","/welcome"}, method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		return "welcome";
	}
	
	
	
	

}
