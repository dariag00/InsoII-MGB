package com.unileon.insoII.mgb.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unileon.insoII.mgb.form.model.TransactionForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Transaction;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.service.AccountService;
import com.unileon.insoII.mgb.service.TransferService;
import com.unileon.insoII.mgb.utils.Constants;

@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	@Autowired
	TransferService transferService;
	
	
	@RequestMapping(value= {"/accountDetail"}, method = RequestMethod.GET)
	public String showAccountDetail(ModelMap model, @RequestParam int accountId, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		
		if(user == null)
			return "redirect:login";
		
		Account userAccount = null;
		
		/*for(Account account:user.getListOfAccounts()) {
			if(account.getId() == accountId)
				userAccount = account;
		}*/
		
		userAccount = accountService.getAccountById(accountId);
		
		
		if(userAccount == null){
			return "redirect:dashboard";
		}
		
		accountService.generateSecretPassword(userAccount);
		
		//System.out.println(userAccount.getAccountOwner().getFullName());
		
		model.addAttribute("account", userAccount);
		
		List<Transaction> transactions = userAccount.getTransactions();
		model.put("transactions", transactions);
		
		model.addAttribute("transfer", new TransactionForm());
		
		return "account_detail";
	}
	
	@RequestMapping(value="/addTransferFromAccount", method=RequestMethod.POST)
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
			
			return "redirect:accountDetail?accountId=" + transactionForm.getAccountId();
		}
		return "redirect:account_detail";
	}
	
	@RequestMapping(value="/deleteAccount", method=RequestMethod.GET)
	public String deleteAccount(ModelMap model, @RequestParam int accountId, HttpSession session,  RedirectAttributes redir) {
		
		Account userAccount = accountService.getAccountById(accountId);
		accountService.deleteAccount(userAccount);
		redir.addFlashAttribute("successMessage", "The account with IBAN: " + userAccount.getIban() + " has been deleted succesfully");
		
		return "redirect:dashboard";
	}
	

}
