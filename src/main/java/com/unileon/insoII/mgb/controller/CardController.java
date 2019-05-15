package com.unileon.insoII.mgb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CardController {
	
	@RequestMapping(value= {"/newCard"}, method = RequestMethod.GET)
	public String showHireNewCard(ModelMap model) {
		return "hire_new_card";
	}

}
