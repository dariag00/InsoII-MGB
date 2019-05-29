package com.unileon.insoII.mgb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.form.model.CardForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.repository.AccountRepository;
import com.unileon.insoII.mgb.repository.CardRepository;
import com.unileon.insoII.mgb.utils.Constants;

@Service
public class CardService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	public int createCard(CardForm cardForm, User user) {
		
		Card card = new Card();
		card.setUser(user);
		card.setSecretPin(cardForm.getSecretPin());
		Account account = accountRepository.findById(Integer.valueOf(cardForm.getIdAccount())).get();
		card.setAccount(account);
		
		cardRepository.save(card);
		
		return Constants.CARD_OK;
	}
	
	public Card getCardById(int id) {
		return cardRepository.findById(id).get();
	}
	
	public void deleteCard(Card card) {
		card.getUser().getCards().remove(card);
		card.getAccount().getCards().remove(card);
		cardRepository.delete(card);
	}
	
	public void changeCardStatus(Card card) {
		
		if(card.getStatus() == Constants.CARD_STATUS_ACTIVE)
			card.setStatus(Constants.CARD_STATUS_INACTIVE);
		else if(card.getStatus() == Constants.CARD_STATUS_INACTIVE)
			card.setStatus(Constants.CARD_STATUS_ACTIVE);
		
		cardRepository.save(card);
	}
	
	public int changePin(CardForm cardForm, int cardId) {
		
		Card card = cardRepository.findById(cardId).get();
		System.out.println(card.getSecretPin() + " " + cardForm.getOldPin());
		if(!card.getSecretPin().equals(cardForm.getOldPin())) {
			return Constants.CHANGE_PIN_INVALID_PIN;
		}
		
		if(!cardForm.getSecretPin().contentEquals(cardForm.getConfirmSecretPin())) {
			return Constants.CHANGE_PIN_DO_NOT_MATCH;
		}
		
		card.setSecretPin(cardForm.getConfirmSecretPin());
		cardRepository.save(card);
		
		return Constants.CHANGE_PIN_OK;
		
	}

}
