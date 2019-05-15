package com.unileon.insoII.mgb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransferController {
	
	@RequestMapping(value= {"/newTransfer"}, method = RequestMethod.GET)
	public String showNewTransfer(ModelMap model) {
		return "send_new_transfer";
	}

}
