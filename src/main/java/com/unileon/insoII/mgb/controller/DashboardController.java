package com.unileon.insoII.mgb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {
	
	@RequestMapping(value= {"/welcome"}, method = RequestMethod.GET)
	public String showWelcome(ModelMap model) {
		return "welcome";
	}
	
	@RequestMapping(value= {"/dashboard"}, method = RequestMethod.GET)
	public String showDashboard(ModelMap model) {
		return "dashboard";
	}

}
