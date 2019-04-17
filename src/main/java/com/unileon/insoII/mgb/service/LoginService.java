package com.unileon.insoII.mgb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	UserRepository userRepository;
	
	public boolean logIn(String username, String password) {
		User user = userRepository.findById(1).get();
		if(user.getPassword().equals(password))
			return true;
		else
			return false;
	}

}
