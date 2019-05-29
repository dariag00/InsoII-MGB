package com.unileon.insoII.mgb.form.model;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.User;

public class CardForm {

	private String secretPin;
	private int idAccount;
	private String oldPin;
	private String confirmSecretPin;
	
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
	public String getOldPin() {
		return oldPin;
	}
	public void setOldPin(String oldPin) {
		this.oldPin = oldPin;
	}
	public String getConfirmSecretPin() {
		return confirmSecretPin;
	}
	public void setConfirmSecretPin(String confirmSecretPin) {
		this.confirmSecretPin = confirmSecretPin;
	}

}
