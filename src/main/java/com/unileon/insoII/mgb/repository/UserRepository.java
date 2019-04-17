package com.unileon.insoII.mgb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.unileon.insoII.mgb.model.User;

public interface UserRepository extends CrudRepository<User, Integer>  {
	
	 //List<User> findByDni(String dni);
	 
}
