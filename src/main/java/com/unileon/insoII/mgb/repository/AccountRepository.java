package com.unileon.insoII.mgb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.User;

public interface AccountRepository  extends CrudRepository<Account, Integer> {
	List<Account> findById(int id);
	List<Account> findByIban(String iban);
}
