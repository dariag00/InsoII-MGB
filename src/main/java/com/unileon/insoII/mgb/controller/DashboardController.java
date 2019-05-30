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

import com.unileon.insoII.mgb.form.model.CardForm;
import com.unileon.insoII.mgb.form.model.OperationForm;
import com.unileon.insoII.mgb.form.model.UserForm;
import com.unileon.insoII.mgb.form.model.TransactionForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.Transaction;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.service.LoginService;
import com.unileon.insoII.mgb.service.OperationService;
import com.unileon.insoII.mgb.service.TransferService;
import com.unileon.insoII.mgb.service.AccountService;
import com.unileon.insoII.mgb.service.CardService;
import com.unileon.insoII.mgb.service.UserService;
import com.unileon.insoII.mgb.utils.Constants;

@Controller
public class DashboardController {
	
	@Autowired
	UserService userService;
	@Autowired
	LoginService loginService;
	@Autowired
	AccountService accountService;

	@Autowired
	TransferService transferService;
	@Autowired
	OperationService operationService;
	@Autowired
	CardService cardService;
	
	
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
		model.addAttribute("newCard", new CardForm());
		model.addAttribute("newOperation", new OperationForm());
		model.addAttribute("userForm", new UserForm());
		/*model.addAttribute("successMessage", "Transfer done succesfuly");
		model.addAttribute("errorMessage", "No hay suficientes fondos.");*/
		
		return "dashboard";
	}
	
	@RequestMapping(value="/addCard", method=RequestMethod.POST)
	public String addCard(@ModelAttribute("newCard") CardForm cardForm, BindingResult bindingResult, ModelMap model, RedirectAttributes redir, HttpSession session) {
		if(!bindingResult.hasErrors()) {
			System.out.println("Entro");
			User user = (User) session.getAttribute("user");
			if(user==null) {
				redir.addFlashAttribute("errorMessage", "User is not logged in / User lost");
				return "redirect:login";			
			}
			int result = cardService.createCard(cardForm,user);
			
			System.out.println("Result: "+ result);
			
			if (result==Constants.CARD_OK)
				redir.addFlashAttribute("successMessage", "Card added successfully");
			//TODO Añadir errores
			
			return "redirect:dashboard";
		}
		System.out.println("No-Entro");
		return "redirect:dashboard";
	}

	@RequestMapping(value="/asociateAccountFromDashboard", method=RequestMethod.POST)
	public String associateUserToAccount(@ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, ModelMap model,  RedirectAttributes redir,  HttpSession session) {
		
		
		if(!bindingResult.hasErrors()) {
			
			User user = (User) session.getAttribute("user");
			if(user==null) {
				redir.addFlashAttribute("errorMessage", "User is not logged in / User lost");
				return "redirect:login";			
			}
			
			int result = accountService.associateUserToAccount(user, userForm);
			
			if(result == Constants.CREATE_ACCOUNT_OK) {
				redir.addFlashAttribute("successMessage", "El usuario se ha asociado correctamente a la cuenta.");
				return "redirect:dashboard";
			}else if (result == Constants.CREATE_ACCOUNT_INCORRECT_PASSWORD){
				redir.addFlashAttribute("errorMessage", "La contraseña secreta para unirte a una cuenta es incorrecta");
				return "redirect:dashboard";
			}else if (result == Constants.CREATE_ACCOUNT_INCORRECT_ACCOUNT_ID){
				redir.addFlashAttribute("errorMessage", "El ID del dueño de la cuenta es incorrecto");
				return "redirect:dashboard";
			}else {
				redir.addFlashAttribute("errorMessage", "Error desconocido al asociarte a la cuenta");
				return "redirect:dashboard";
			}
			
			
		}
		
		return "redirect:dashboard";
	}
	
	@RequestMapping(value="/createAccountFromDashboard", method=RequestMethod.GET)
	public String associateUserToAccount(ModelMap model,  RedirectAttributes redir,  HttpSession session) {
		
		
		User user = (User) session.getAttribute("user");
		if(user==null) {
			redir.addFlashAttribute("errorMessage", "User is not logged in / User lost");
			return "redirect:login";			
		}
		
		int result = accountService.createAccount(user);
		
		if(result == Constants.CREATE_ACCOUNT_OK) {
			redir.addFlashAttribute("successMessage", "Se ha creado correctamente la cuenta.");
			return "redirect:dashboard";
		}
			
		return "redirect:dashboard";
	}

	
	
	
	@RequestMapping(value="/addTransfer", method=RequestMethod.POST)
	public String addTransfer(@ModelAttribute("transfer") TransactionForm transactionForm, BindingResult bindingResult, ModelMap model,  HttpSession session, RedirectAttributes redir) {
		
		if (!bindingResult.hasErrors()) {
			System.out.println("Entro");
			User user = (User) session.getAttribute("user");
			if(user==null) {
				redir.addFlashAttribute("errorMessage", "User is not logged in / User lost");
				return "redirect:login";			
			}
			int result = transferService.createTransfer(transactionForm);
			System.out.println("Result: " + result);
			
			if(result == Constants.TRANSFER_OK)
				redir.addFlashAttribute("successMessage", "Transfer done successfuly");
			if(result == Constants.TRANSFER_IBAN_NOT_ENOUGH_FUNDS)
				redir.addFlashAttribute("errorMessage","We cant make the transfer because there are not enough funds in the selected account.");
			if(result == Constants.TRANSFER_IBAN_NOT_FOUND)
				redir.addFlashAttribute("errorMessage","We cant make the transfer because MGB doesnt have an account with that IBAN");
			
			//redir.addFlashAttribute("errorMessage","We cant make the transfer because there are not enough funds.");
			
			return "redirect:dashboard";
		}
		return "redirect:dashboard";
	}

	@RequestMapping(value="/addOperation", method=RequestMethod.POST)
	public String addOperation(@ModelAttribute("newOperation") OperationForm operationForm, BindingResult bindingResult, ModelMap model,  RedirectAttributes redir, HttpSession session,Card card) {
		
		if (!bindingResult.hasErrors()) {
			System.out.println("Entro");
			
			int result = operationService.createOperation(operationForm);
			System.out.println("Result: " + result);
			
			if(result == Constants.OPERATION_OK)
				redir.addFlashAttribute("successMessage", "Operation done successfuly");
			if(result == Constants.OPERATION_IBAN_NOT_ENOUGH_FUNDS)
				redir.addFlashAttribute("errorMessage","We cant make the transfer because there are not enough funds in the selected account.");
			if(result == Constants.OPERATION_ERROR)
				redir.addFlashAttribute("errorMessage","SOMETHING WENT WRONG");
			
			//redir.addFlashAttribute("errorMessage","We cant make the transfer because there are not enough funds.");
			
			return "redirect:dashboard";
		}
		return "redirect:dashboard";
	}
}
