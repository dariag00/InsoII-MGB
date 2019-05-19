package com.unileon.insoII.mgb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.model.UserAccount;

public interface UserRepository extends CrudRepository<User, Integer>  {
	
	 List<User> findByEmail(String email);
	 
}
