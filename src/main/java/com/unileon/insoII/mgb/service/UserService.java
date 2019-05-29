package com.unileon.insoII.mgb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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
import com.unileon.insoII.mgb.service.AccountService;
import com.unileon.insoII.mgb.utils.Constants;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CardRepository cardRepository;
	@Autowired
	UserAccountRepository uacRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	AccountService accountService;
	
	public int createUser(UserForm userForm) {
		
		User user = new User();
		user.setNombre(userForm.getName());
		user.setApellidos(userForm.getSurname());
		user.setEmail(userForm.getEmail());
		user.setAddress(userForm.getAddress());
		user.setCity(userForm.getCity());
		user.setCountry(userForm.getCountry());
		user.setFirstLogin(true);
		
		if(!userForm.getPassword().equals(userForm.getConfirmPassword()))
			return Constants.CREATE_ACCOUNT_PASSWORDS_NO_MATCH;
		//Hay que comprobar que no existe ya una cuenta con ese DNI y que no exista una cuenta ya con ese email
		user.setPassword(userForm.getPassword());
		user.setDni(userForm.getId());
		
		Account account = new Account();
		//Ahora miramos si es una cuenta nueva o no
		if(userForm.getAccountId().isEmpty()) {
			//Es una cuenta nueva
			account.setCreationDate(new Date());
			account.addUser(user);
			account.setCurrency(Constants.CURRENCY_EURO);
			
			//Creamos una tarjeta nueva asociada a esta cuenta
			Card card = new Card();
			card.setAccount(account);
			card.setUser(user);
			card.setStatus(Constants.CARD_STATUS_ACTIVE);
			
			user = userRepository.save(user);
			account = accountRepository.save(account);
			cardRepository.save(card);
			
			
			user = (User) userRepository.findByEmail(user.getEmail()).get(0);
			
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
			
			//Realizamos la transaccion de bienvenida
			Transaction transaction = new Transaction();
			transaction.setBeneficiary(user.getFullName());
			transaction.setCommentary("Transferencia de Bienvenida a MGB");
			transaction.setDestinyAccount(ac.getAccount());
			transaction.setTransactionDate(new Date());
			transaction.setValue(50);
			account.addBalance(50);
			
			transactionRepository.save(transaction);
			accountRepository.save(account);
		}else {
			System.out.println("ACCId:" + userForm.getAccountId() + " pass: " + userForm.getSecretPassword() + " id U: " + userForm.getAccountOwnerId());
			
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
				
				user = userRepository.save(user);
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
				
				transactionRepository.save(transaction);
				
			}else {
				return Constants.CREATE_ACCOUNT_INCORRECT_PASSWORD;
			}
			
		}
	
		
		return Constants.CREATE_ACCOUNT_OK;
		
	}
	
	public User getUserById(String email) {
		return userRepository.findByEmail(email).get(0);
	}
	public User getUser(int id) {
		return userRepository.findById(id).get();
	}
	public User markFirstLoginAsResolved(User user, HttpSession session) {
		user = userRepository.findByEmail(user.getEmail()).get(0);
		user.setFirstLogin(false);
		user=  userRepository.save(user);
		return user;
	}
	public int editUser(UserForm userForm, User user) {
		
		
		user.setNombre(userForm.getName());
		user.setApellidos(userForm.getSurname());
		user.setAddress(userForm.getAddress());
		user.setCity(userForm.getCity());
		user.setCountry(userForm.getCountry());
		if( (!userForm.getOldPassword().equals(""))){
			if(userForm.getPassword().equals("")) {
				return Constants.EDIT_PASSWORD_EMPTY;
			}
					
			if(userForm.getOldPassword().contentEquals(user.getPassword())){
				if(userForm.getPassword().equals(userForm.getConfirmPassword())) {
					user.setPassword(userForm.getPassword());
				}
				else {
					return Constants.EDIT_PASSWORD_NOT_MATCH;
				}
				
			}
			else {
				return Constants.EDIT_PASSWORD_OLD_INCORRECT;
			}
		}
		else if(userForm.getOldPassword().contentEquals("")){
			return Constants.EDIT_PASSWORD_EMPTY_OLD;			
		}
		
		
	
	
		user = userRepository.save(user);
		
		return Constants.EDIT_USER_OK;
		
	}

	public void deleteUser(User user) {
		//Borrar 
		for(Account account:user.getListOfAccounts())
			accountService.deleteAccount(account);		
		userRepository.delete(user);
		
	}
	
	

}
