package com.unileon.insoII.mgb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;

	public void generateSecretPassword(Account account) {
		account.generateSecretPassword();
		accountRepository.save(account);
	}
	
}
