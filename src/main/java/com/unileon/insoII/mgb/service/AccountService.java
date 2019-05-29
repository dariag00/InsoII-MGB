package com.unileon.insoII.mgb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.Transaction;
import com.unileon.insoII.mgb.model.UserAccount;
import com.unileon.insoII.mgb.repository.AccountRepository;
import com.unileon.insoII.mgb.repository.CardRepository;
import com.unileon.insoII.mgb.repository.TransactionRepository;
import com.unileon.insoII.mgb.repository.UserAccountRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CardRepository cardRepository;
	@Autowired
	UserAccountRepository uacRepository;
	@Autowired
	TransactionRepository transferRepository;

	public void generateSecretPassword(Account account) {
		account.generateSecretPassword();
		accountRepository.save(account);
	}
	
	public void deleteAccount(Account account) {
		
		for(Card card:account.getCards()) {
			//Borrar movimientos
			account.getCards().remove(card);
			card.getUser().getCards().remove(card);
			System.out.println("Id:" + card.getId());
			
			cardRepository.delete(card);
		}
		
		for(UserAccount uac : account.getUsers()) {
			if(uac.getAccount().getId() == account.getId()) {
				uac.getUser().getAccounts().remove(uac);
				account.getUsers().remove(uac);
				uacRepository.delete(uac);
			}
		}
		
		for(Transaction trans: account.getTransactions()) {
			if(trans.getOriginAccount() != null && trans.getOriginAccount().getId() == account.getId()) {
				trans.setOriginAccount(null);
			}
			if(trans.getDestinyAccount() != null && trans.getDestinyAccount().getId() == account.getId()) {
				trans.setDestinyAccount(null);
			}
			transferRepository.save(trans);
		}
		
		accountRepository.delete(account);
		
		/*for(Card card:account.getCards()) {
			//Borrar movimientos
			account.getCards().remove(card);
			card.getUser().getCards().remove(card);
			System.out.println("Id:" + card.getId());
			
			cardRepository.delete(card);
		}*/
		
	}
	
	public Account getAccountById(int id) {
		return accountRepository.findById(id).get(0);
	}
	
}
