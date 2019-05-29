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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unileon.insoII.mgb.form.model.CardForm;
import com.unileon.insoII.mgb.form.model.OperationForm;
import com.unileon.insoII.mgb.form.model.TransactionForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.Operation;
import com.unileon.insoII.mgb.model.Transaction;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.service.CardService;
import com.unileon.insoII.mgb.service.OperationService;
import com.unileon.insoII.mgb.utils.Constants;

@Controller
public class CardController {
	
	@Autowired
	CardService cardService;
	@Autowired
	OperationService operationService;
	
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
	public String deleteCard(ModelMap model, @RequestParam int cardId, HttpSession session, RedirectAttributes redir) {
		Card card = cardService.getCardById(cardId);
		cardService.deleteCard(card);
		redir.addFlashAttribute("successMessage","The card with Number: " + card.getFormattedCardNumber() + " has been deleted succesfully");
		return "redirect:dashboard";
	}
	
	@RequestMapping(value= {"/changeCardStatus"}, method = RequestMethod.GET)
	public String changeCardStatus(ModelMap model, @RequestParam int cardId, HttpSession session, RedirectAttributes redir) {
		Card card = cardService.getCardById(cardId);
		cardService.changeCardStatus(card);
		redir.addFlashAttribute("successMessage","The card with Number: " + card.getFormattedCardNumber() + " has changed it status correctly");
		return "redirect:cardDetail?cardId="+cardId;
	}
	
	@RequestMapping(value= {"/changePin"}, method = RequestMethod.POST)
	public String changePin(ModelMap model, @RequestParam int cardId, HttpSession session, @ModelAttribute("cardForm") CardForm cardForm, BindingResult bindingResult, RedirectAttributes redir) {
		
		if(!bindingResult.hasErrors()) {
			
			User user = (User) session.getAttribute("user");
			if(user==null) {
				redir.addFlashAttribute("errorMessage", "User is not logged in / User lost");
				return "redirect:login";			
			}
			
			int result = cardService.changePin(cardForm, cardId);
			
			if(result == Constants.CHANGE_PIN_OK) {
				redir.addFlashAttribute("successMessage","The card PIN has been changed succesfully");
			}else if(result == Constants.CHANGE_PIN_DO_NOT_MATCH) {
				redir.addFlashAttribute("errorMessage","The new PINs do not match");
			}else if(result == Constants.CHANGE_PIN_INVALID_PIN) {
				redir.addFlashAttribute("errorMessage","The old PIN do not match with the current PIN");
			}
			
		}
		
		return "redirect:cardDetail?cardId="+cardId;
	}
	
	@RequestMapping(value="/addOperationFromCard", method=RequestMethod.POST)
	public String addOperationFromCard(@ModelAttribute("newOperation") OperationForm operationForm, BindingResult bindingResult, ModelMap model,  RedirectAttributes redir, HttpSession session,Card card) {
		
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
			
			return "redirect:cardDetail?cardId="+operationForm.getCardId();
		}
		return "redirect:dashboard";
	}
	

	

}
