package com.unileon.insoII.mgb.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.form.model.UserForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.repository.AccountRepository;
import com.unileon.insoII.mgb.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	
	public boolean createUser(UserForm userForm) {
		
		User user = new User();
		user.setNombre(userForm.getName());
		user.setApellidos(userForm.getSurname());
		user.setEmail(userForm.getEmail());
		user.setAddress(userForm.getAddress());
		user.setCity(userForm.getCity());
		user.setCountry(userForm.getCountry());
		
		if(!userForm.getPassword().equals(userForm.getConfirmPassword()))
			return false;
		
		user.setPassword(userForm.getPassword());
		user.setDni(userForm.getId());
		Account account = new Account();
		//Ahora miramos si es una cuenta nueva o no
		if(userForm.getAccountId().isEmpty()) {
			//Es una cuenta nueva
			
			account.setBalance(0);
			account.setCreationDate(new Date());
			account.addUser(user);
		}
		
		userRepository.save(user);
		accountRepository.save(account);
		
		return true;
		
	}

}
