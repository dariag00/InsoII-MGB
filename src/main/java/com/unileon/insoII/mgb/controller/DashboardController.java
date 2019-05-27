package com.unileon.insoII.mgb.controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unileon.insoII.mgb.form.model.TransactionForm;
import com.unileon.insoII.mgb.form.model.UserForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.Transaction;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.service.LoginService;
import com.unileon.insoII.mgb.service.TransferService;
import com.unileon.insoII.mgb.service.UserService;
import com.unileon.insoII.mgb.utils.Constants;

@Controller
public class DashboardController {
	
	@Autowired
	UserService userService;
	@Autowired
	LoginService loginService;
	
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
		System.out.println("ENTRO");
		
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			System.out.println("sesion muerta, vamos al login");
			return "redirect:login";
		}
		//Recargamos la sesion con nuevos datos o si se hace f5
		user = userService.getUserById(user.getEmail());
		loginService.createSession(user, session);
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*if(user.isFirstLogin())
			user = userService.markFirstLoginAsResolved(user, session);*/
		
		System.out.println(user.toString());
		//Añadimos el usuario al model
		model.put("user", user);
		//Obtenemos la lista de cuentas del usuario
		List<Account> accountList = user.getListOfAccounts();
		//System.out.println("A:" + accountList.get(0).getBalance());
		model.put("accounts", accountList);
		
		Set<Card> cards = user.getCards();
		model.put("cards", cards);
		
		List<Transaction> transactions = user.getAllTransactions();
		model.put("transactions", transactions);
		
		model.addAttribute("transfer", new TransactionForm());
		/*model.addAttribute("successMessage", "Transfer done succesfuly");
		model.addAttribute("errorMessage", "No hay suficientes fondos.");*/
		
		return "dashboard";
	}
	
	@RequestMapping(value="/addTransfer", method=RequestMethod.POST)
	public String addTransfer(@ModelAttribute("transfer") TransactionForm transactionForm, BindingResult bindingResult, ModelMap model,  RedirectAttributes redir) {
		
		if (!bindingResult.hasErrors()) {
			System.out.println("Entro");
			
			int result = transferService.createTransfer(transactionForm);
			System.out.println("Result: " + result);
			
			if(result == Constants.TRANSFER_OK)
				redir.addFlashAttribute("successMessage", "Transfer done succesfuly");
			if(result == Constants.TRANSFER_IBAN_NOT_ENOUGH_FUNDS)
				redir.addFlashAttribute("errorMessage","We cant make the transfer because there are not enough funds in the selected account.");
			if(result == Constants.TRANSFER_IBAN_NOT_FOUND)
				redir.addFlashAttribute("errorMessage","We cant make the transfer because MGB doesnt have an account with that IBAN");
			
			//redir.addFlashAttribute("errorMessage","We cant make the transfer because there are not enough funds.");
			
			return "redirect:dashboard";
		}
		return "redirect:dashboard";
	}

}
