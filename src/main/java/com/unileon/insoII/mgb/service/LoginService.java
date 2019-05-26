package com.unileon.insoII.mgb.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	UserRepository userRepository;
	
	public boolean logIn(String username, String password, HttpSession session) {
		List<User> userList = userRepository.findByEmail(username);
		User user = null;
		if(!userList.isEmpty())
			user = userList.get(0);
		if(user!= null && user.getPassword().equals(password)) {
			createSession(user, session);
			System.out.println(session.getAttribute("user").toString());
			return true;
		}
		else
			return false;
	}
	
	public void createSession(User user, HttpSession session) {
		session.setAttribute("user", user);
	}

}
