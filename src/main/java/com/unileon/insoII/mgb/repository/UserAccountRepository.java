package com.unileon.insoII.mgb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.unileon.insoII.mgb.model.User;
import com.unileon.insoII.mgb.model.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Integer>  {
	
	 List<User> findByUserId(int userId);

}
