package com.unileon.insoII.mgb.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unileon.insoII.mgb.form.model.CardForm;
import com.unileon.insoII.mgb.form.model.OperationForm;
import com.unileon.insoII.mgb.form.model.TransactionForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.Operation;
import com.unileon.insoII.mgb.model.Transaction;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.service.CardService;

@Controller
public class CardController {
	
	@Autowired
	CardService cardService;
	
	@RequestMapping(value= {"/cardDetail"}, method = RequestMethod.GET)
	public String showCardDetail(ModelMap model, @RequestParam int cardId, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		
		if(user == null)
			return "redirect:login";
		
		Card userCard = null;
		
		/*for(Account account:user.getListOfAccounts()) {
			if(account.getId() == accountId)
				userAccount = account;
		}*/
		
		userCard = cardService.getCardById(cardId);
		
		
		if(userCard == null){
			return "redirect:dashboard";
		}
		
		
		//System.out.println(userAccount.getAccountOwner().getFullName());
		
		model.addAttribute("card", userCard);
		
		Set<Operation> operations = userCard.getOperations();
		model.put("operations", operations);
		
		model.addAttribute("newOperation", new OperationForm());
		model.addAttribute("cardForm", new CardForm());
		
		return "card_detail";
	}
	
	@RequestMapping(value= {"/deleteCard"}, method = RequestMethod.GET)
	public String deleteCard(ModelMap model, @RequestParam int cardId, HttpSession session) {
		return null;
	}
	
	@RequestMapping(value= {"/changePin"}, method = RequestMethod.GET)
	public String changePin(ModelMap model, @RequestParam int cardId, HttpSession session, @ModelAttribute("cardForm") CardForm cardForm, BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			
		}
		
		return "card_detail";
	}
	

	

}
