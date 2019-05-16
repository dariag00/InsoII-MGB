package com.unileon.insoII.mgb.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.form.model.UserForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.repository.AccountRepository;
import com.unileon.insoII.mgb.repository.CardRepository;
import com.unileon.insoII.mgb.repository.UserRepository;
import com.unileon.insoII.mgb.utils.Constants;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CardRepository cardRepository;
	
	public User createUser(UserForm userForm) {
		
		User user = new User();
		user.setNombre(userForm.getName());
		user.setApellidos(userForm.getSurname());
		user.setEmail(userForm.getEmail());
		user.setAddress(userForm.getAddress());
		user.setCity(userForm.getCity());
		user.setCountry(userForm.getCountry());
		user.setFirstLogin(true);
		
		if(!userForm.getPassword().equals(userForm.getConfirmPassword()))
			return null;
		
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
			accountRepository.save(account);
			cardRepository.save(card);
			
		}
	
		
		return user;
		
	}
	
	public User getUserById(String email) {
		return userRepository.findByEmail(email).get(0);
	}
	
	public User markFirstLoginAsResolved(User user, HttpSession session) {
		user = userRepository.findByEmail(user.getEmail()).get(0);
		user.setFirstLogin(false);
		user=  userRepository.save(user);
		return user;
	}
	

}
