package com.unileon.insoII.mgb.form.model;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.unileon.insoII.mgb.model.Account;
import com.unileon.insoII.mgb.model.User;

public class CardForm {

	private String cvv;
	private String secretPin;
	private int status;
	private User user;
	private String cardNumber;
	private Account account;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getSecretPin() {
		return secretPin;
	}
	public void setSecretPin(String secretPin) {
		this.secretPin = secretPin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
