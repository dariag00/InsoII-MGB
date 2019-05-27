package com.unileon.insoII.mgb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.form.model.TransactionForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Transaction;
import com.unileon.insoII.mgb.repository.AccountRepository;
import com.unileon.insoII.mgb.repository.TransactionRepository;
import com.unileon.insoII.mgb.utils.Constants;

@Service
public class TransferService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	public int createTransfer(TransactionForm transactionForm) {
		
		Transaction transaction = new Transaction();
		
		transaction.setBeneficiary(transactionForm.getBeneficiary());
		transaction.setCommentary(transactionForm.getCommentary());
		Account originAccount = accountRepository.findById(Integer.valueOf(transactionForm.getAccountId())).get();
		transaction.setOriginAccount(originAccount);
		transaction.setTransactionDate(new Date());
		transaction.setValue(transactionForm.getValue());
		
		if(transactionForm.getValue() > originAccount.getBalance()) {
			System.out.println("Se ha intentado realizar una transferencia pero la cuenta no tenía suficientes fondos");
			return Constants.TRANSFER_IBAN_NOT_ENOUGH_FUNDS;
		}
		
		if(transactionForm.getType() == 0) {
			//Se trata de una transferencia normal
			transaction.setDestinyIban(transactionForm.getIban());
			originAccount.substractBalance(transactionForm.getValue());
		}else if(transactionForm.getType() == 1) {
			//Se trata de una transferencia entre cuentas de MGB
			String formattedIban = transactionForm.getIban().trim().replace(" ", "");
			List<Account> account = accountRepository.findByIban(formattedIban);
			Account destinyAccount = null;
			if(!account.isEmpty()) {
				destinyAccount = accountRepository.findByIban(formattedIban).get(0);
			}
			
			if(destinyAccount != null) {
				transaction.setDestinyAccount(destinyAccount);
				transaction.setDestinyIban(transactionForm.getIban());
				destinyAccount.addBalance(transaction.getValue());
				originAccount.substractBalance(transactionForm.getValue());
			}else {
				//No se ha podido encontrar la cuenta
				System.out.println("No se ha podido encontrar la cuenta");
				return Constants.TRANSFER_IBAN_NOT_FOUND;
			}
		}
		
		transactionRepository.save(transaction);
		accountRepository.save(originAccount);
		
		return Constants.TRANSFER_OK;
	}

}
