package com.unileon.insoII.mgb.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.unileon.insoII.mgb.utils.Constants;


@Entity
@Table(name = "Card")
public class Card implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String cardNumber;
	private int status;
	@ManyToOne
	@JoinColumn
	private User user;
	@ManyToOne
	@JoinColumn
	private Account account;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "card", cascade = CascadeType.ALL)
	private Set<Operation> operations = new HashSet<>();
	private String cvv;
	private String secretPin;
	
	public Card() {
		generateRandomCardNumber();
		generateRandomPin();
		generateCVV();
		this.status=Constants.CARD_STATUS_ACTIVE;
	}
	

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
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
	public Set<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
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

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public void generateRandomCardNumber() {
		
		String cardNumber = "";
		
		Random rand = new Random();
		
		for (int i = 0; i < 16; i++){
	        int n = rand.nextInt(10) + 0;
	        cardNumber += Integer.toString(n);
	    }
		
		this.cardNumber = cardNumber;
	}
	
	public void generateCVV() {
		
		String cvv = "";
		
		Random rand = new Random();
		
		for (int i = 0; i < 3; i++){
	        int n = rand.nextInt(10) + 0;
	        cvv += Integer.toString(n);
	    }
		
		this.cvv = cvv;
	}
	
	public void generateRandomPin() {
		
		Random rand = new Random();
		
		String pinNumber = "";
		
		for (int i = 0; i < 4; i++){
			int n = rand.nextInt(10) + 0;
			pinNumber += Integer.toString(n);
		}
		
		this.secretPin = pinNumber;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public String getFormattedCardNumber() {
	StringBuffer formattedCardNumber = new StringBuffer();
		
		for (int i = 0; i < this.cardNumber.length(); i++){
	        if(i % 4 == 0)
	        	formattedCardNumber.append(" ");
	        formattedCardNumber.append(this.cardNumber.charAt(i));
	    }
		
		return formattedCardNumber.toString();
	}
	
	public String getStringStatus() {
		
		if(this.status == Constants.CARD_STATUS_ACTIVE)
			return "Active";
		else if(this.status == Constants.CARD_STATUS_INACTIVE)
			return "Inactive";
		else
			return "";
	}

}
