package com.unileon.insoII.mgb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unileon.insoII.mgb.form.model.OperationForm;
import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.Card;
import com.unileon.insoII.mgb.model.Operation;
import com.unileon.insoII.mgb.repository.AccountRepository;
import com.unileon.insoII.mgb.repository.CardRepository;
import com.unileon.insoII.mgb.repository.OperationRepository;
import com.unileon.insoII.mgb.utils.Constants;

@Service
public class OperationService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	OperationRepository operationRepository;
	
	public int createOperation(OperationForm operationForm) {
		
		Operation operation = new Operation();
		
		operation.setLocation(operationForm.getLocation());
		Card card= cardRepository.findById(Integer.valueOf(operationForm.getCardId())).get();
		operation.setCard(card);
		operation.setValue(operationForm.getValue());
		operation.setOperationDate(new Date());
		Account account= card.getAccount();
		
		if(operationForm.getValue() > account.getBalance()) {
			System.out.println("Se ha intentado realizar una transferencia pero la cuenta no tenía suficientes fondos");
			return Constants.OPERATION_IBAN_NOT_ENOUGH_FUNDS;
		}
		
		if(operationForm.getType() == Constants.OPERATION_TYPE_PAYMENT) {
			//Se trata de un pago
			operation.setType(Constants.OPERATION_TYPE_PAYMENT);
			account.substractBalance(operationForm.getValue());
		}else if(operationForm.getType() == Constants.OPERATION_TYPE_WITHDRAWAL) {
			//Se trata de un withdrawn
			operation.setType(Constants.OPERATION_TYPE_WITHDRAWAL);
			account.substractBalance(operationForm.getValue());
			operation.setLocation("MGB Office");
			
			
		}else {
			//El tipo es erroneo
			System.out.println("No se ha podido realizar la operacion");
			return Constants.OPERATION_ERROR;
			
		}
		
		operationRepository.save(operation);
		accountRepository.save(account);
		
		return Constants.TRANSFER_OK;
	}

}
