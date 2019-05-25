package com.unileon.insoII.mgb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.User;

@Controller
public class AccountController {
	
	
	@RequestMapping(value= {"/accountDetail"}, method = RequestMethod.GET)
	public String showAccountDetail(ModelMap model, @RequestParam int accountId, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		
		if(user == null)
			return "redirect:login";
		
		Account userAccount = null;
		
		for(Account account:user.getListOfAccounts()) {
			if(account.getId() == accountId)
				userAccount = account;
		}
		
		
		if(userAccount == null){
			return "redirect:dashboard";
		}
		
		//System.out.println(userAccount.getAccountOwner().getFullName());
		
		model.addAttribute("account", userAccount);
		
		return "account_detail";
	}
	

}
