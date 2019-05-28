package com.unileon.insoII.mgb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.form.model.CardForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.repository.AccountRepository;
import com.unileon.insoII.mgb.repository.CardRepository;
import com.unileon.insoII.mgb.utils.Constants;

@Service
public class CardService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	public int createCard(CardForm cardForm) {
		
		Card card = new Card();
		
		card.setSecretPin(cardForm.getSecretPin());
		card.setAccount(cardForm.getAccount());
		cardRepository.save(card);
		
		return Constants.CARD_OK;
	}

}
