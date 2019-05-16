package com.unileon.insoII.mgb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unileon.insoII.mgb.form.model.TransactionForm;
import com.unileon.insoII.mgb.form.model.UserForm;
import com.unileon.insoII.mgb.model.Transaction;

@Controller
public class TransferController {
	
	@RequestMapping(value= {"/newTransfer"}, method = RequestMethod.GET)
	public String showNewTransfer(ModelMap model) {
		model.addAttribute("transfer", new TransactionForm());
		return "send_new_transfer";
	}

}
