package com.unileon.insoII.mgb.form.model;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.User;

public class CardForm {

	private String secretPin;
	private int idAccount;
	
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	
	public String getSecretPin() {
		return secretPin;
	}
	public void setSecretPin(String secretPin) {
		this.secretPin = secretPin;
	}
	

}
