package com.unileon.insoII.mgb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {
	
	
	@RequestMapping(value= {"/accountDetail"}, method = RequestMethod.GET)
	public String showAccountDetail(ModelMap model) {
		return "account_detail";
	}
	

}
