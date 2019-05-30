package com.unileon.insoII.mgb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.form.model.UserForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.Transaction;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.model.UserAccount;
import com.unileon.insoII.mgb.repository.AccountRepository;
import com.unileon.insoII.mgb.repository.CardRepository;
import com.unileon.insoII.mgb.repository.TransactionRepository;
import com.unileon.insoII.mgb.repository.UserAccountRepository;
import com.unileon.insoII.mgb.repository.UserRepository;
import com.unileon.insoII.mgb.utils.Constants;

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
	
	@Autowired
	UserRepository userRepository;

	public void generateSecretPassword(Account account) {
		account.generateSecretPassword();
		accountRepository.save(account);
	}
	
	public void deleteAccount(Account account) {
		
		List<Card> cardToDelete= new ArrayList<>();
		List<UserAccount> uAcToDelete= new ArrayList<>();
		for(Card card:account.getCards()) {
			//Borrar movimientos
			cardToDelete.add(card);
		}
		for(Card card : cardToDelete) {

			account.getCards().remove(card);
			card.getUser().getCards().remove(card);
			System.out.println("Id:" + card.getId());
			
			cardRepository.delete(card);
		}
		for(UserAccount uac : account.getUsers()) {
			if(uac.getAccount().getId() == account.getId()) {
				uAcToDelete.add(uac);
			}
		}
		for(UserAccount uac : uAcToDelete) {
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
	
	public int createAccount(User user) {
		
		Account account = new Account();
		account.setCreationDate(new Date());
		account.addUser(user);
		account.setCurrency(Constants.CURRENCY_EURO);
		
		//Creamos una tarjeta nueva asociada a esta cuenta
		Card card = new Card();
		card.setAccount(account);
		card.setUser(user);
		card.setStatus(Constants.CARD_STATUS_ACTIVE);
		
		account = accountRepository.save(account);
		cardRepository.save(card);
		
		UserAccount ac = null;
		List<UserAccount> uacs = new ArrayList<>();
		Iterable<UserAccount> iterable = uacRepository.findAll();
		iterable.forEach(uacs::add);
		for(UserAccount us : uacs) {
			if(us.getUser().getId() == user.getId())
				ac = us;
		}
		
		if(ac != null) {
			ac.setRoleId(Constants.ROLE_ACCOUNT_OWNER);
			uacRepository.save(ac);
		}
	
		accountRepository.save(account);
		
		return Constants.CREATE_ACCOUNT_OK;
		
	}
	
	public int associateUserToAccount(User user, UserForm userForm) {
		
		
		Account account;
		
		Optional<Account> accountL =  accountRepository.findById(Integer.valueOf(userForm.getAccountId()));
		if(accountL == null || !accountL.isPresent())
			return Constants.CREATE_ACCOUNT_INCORRECT_ACCOUNT_ID;
			
		account = accountL.get();
		
		if(account == null)
			return Constants.CREATE_ACCOUNT_INCORRECT_ACCOUNT_ID;
		
		if(account.getSecretPassword().equals(userForm.getSecretPassword()) 
				&& (account.getAccountOwner().getId() == Integer.valueOf(userForm.getAccountOwnerId()))) {
			
			account.addUser(user);
			
			//Creamos una tarjeta nueva asociada a esta cuenta
			Card card = new Card();
			card.setAccount(account);
			card.setUser(user);
			card.setStatus(Constants.CARD_STATUS_ACTIVE);
			
			cardRepository.save(card); 
			
			UserAccount ac = null;
			List<UserAccount> uacs = new ArrayList<>();
			Iterable<UserAccount> iterable = uacRepository.findAll();
			iterable.forEach(uacs::add);
			for(UserAccount us : uacs) {
				if(us.getUser().getId() == user.getId())
					ac = us;
			}
			
			if(ac != null) {
				ac.setRoleId(Constants.ROLE_ACCOUNT_USER);
				uacRepository.save(ac);
			}
			
			
			Transaction transaction = new Transaction();
			transaction.setBeneficiary(user.getFullName());
			transaction.setCommentary("Transferencia gratuita por cortesia de MGB por invitar a " + user.getNombre());
			transaction.setDestinyAccount(account);
			transaction.setTransactionDate(new Date());
			transaction.setValue(25);
			account.addBalance(25);
			
			transferRepository.save(transaction);
			
			return Constants.CREATE_ACCOUNT_OK;
		
		}else {
			return Constants.CREATE_ACCOUNT_INCORRECT_PASSWORD;
		}
	}
	
}
