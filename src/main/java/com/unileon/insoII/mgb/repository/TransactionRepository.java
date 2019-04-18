package com.unileon.insoII.mgb.repository;

import org.springframework.data.repository.CrudRepository;

import com.unileon.insoII.mgb.model.Transaction;

public interface TransactionRepository  extends CrudRepository<Transaction, Integer> {

}
