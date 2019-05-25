package com.unileon.insoII.mgb.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unileon.insoII.mgb.form.model.TransactionForm;
import com.unileon.insoII.mgb.form.model.UserForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.service.TransferService;
import com.unileon.insoII.mgb.service.UserService;

@Controller
public class DashboardController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TransferService transferService;
	
	
	@RequestMapping(value= {"/welcome"}, method = RequestMethod.GET)
	public String showWelcome(ModelMap model) {
		return "welcome";
	}
	
	@PostMapping(value= {"/dashboard"})
	public String updateFirstLogin(ModelMap model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		userService.markFirstLoginAsResolved(user, session);
		return "redirect:dashboard";
	}
	
	@RequestMapping(value= {"/dashboard"}, method = RequestMethod.GET)
	public String showDashboard(ModelMap model, HttpSession session) {
		//Sacamos el usuario de la sesion
		User user = (User) session.getAttribute("user");
		
		if(user == null)
			return "redirect:login";
		
		/*if(user.isFirstLogin())
			user = userService.markFirstLoginAsResolved(user, session);*/
		
		System.out.println(user.toString());
		//Añadimos el usuario al model
		model.put("user", user);
		//Obtenemos la lista de cuentas del usuario
		List<Account> accountList = user.getListOfAccounts();
		model.put("accounts", accountList);
		
		Set<Card> cards = user.getCards();
		model.put("cards", cards);
		model.addAttribute("transfer", new TransactionForm());
		
		return "dashboard";
	}
	
	@RequestMapping(value="/addTransfer", method=RequestMethod.POST)
	public String addTransfer(@ModelAttribute("transfer") TransactionForm transactionForm, BindingResult bindingResult, ModelMap model) {
		
		if (!bindingResult.hasErrors()) {
			
			int result = transferService.createTransfer(transactionForm);
			
			return "redirect:dashboard";
		}
		return "redirect:dashboard";
	}

}
