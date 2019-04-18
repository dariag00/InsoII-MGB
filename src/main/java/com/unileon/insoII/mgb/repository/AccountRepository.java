package com.unileon.insoII.mgb.repository;

import org.springframework.data.repository.CrudRepository;

import com.unileon.insoII.mgb.model.Account;

public interface AccountRepository  extends CrudRepository<Account, Integer> {

}
