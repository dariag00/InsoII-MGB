package com.unileon.insoII.mgb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.*;

import com.unileon.insoII.mgb.utils.Utils;


@Entity
@Table(name = "Account")
public class Account implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserAccount> users = new HashSet<>();
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private Set<Card> cards = new HashSet<>();
	
	@OneToMany(mappedBy = "originAccount", cascade = CascadeType.ALL)
	private Set<Transaction> transactionsDone = new HashSet<>();
	@OneToMany(mappedBy = "destinyAccount", cascade = CascadeType.ALL)
	private Set<Transaction> transactionsRecieved = new HashSet<>();
	
	private int currency;
	private Date creationDate;
	private double balance;
	private String iban;
	
	public Account() {
		generateIban();
		addBalance(50);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<UserAccount> getUsers() {
		return users;
	}
	public void setUsers(Set<UserAccount> accountUsers) {
		this.users = accountUsers;
	}
	public int getCurrency() {
		return currency;
	}
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Set<Card> getCards() {
		return cards;
	}
	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}
	public Set<Transaction> getTransactionsDone() {
		return transactionsDone;
	}
	public void setTransactionsDone(Set<Transaction> transactionsDone) {
		this.transactionsDone = transactionsDone;
	}
	public Set<Transaction> getTransactionsRecieved() {
		return transactionsRecieved;
	}
	public void setTransactionsRecieved(Set<Transaction> transactionsRecieved) {
		this.transactionsRecieved = transactionsRecieved;
	}
	
	public void addUser(User user) {
		UserAccount userAccount = new UserAccount(user, this);
		users.add(userAccount);
	}
	
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public void removeUser(User user) {
        for (Iterator<UserAccount> iterator = users.iterator();
             iterator.hasNext(); ) {
        	UserAccount userAccount = iterator.next();
 
            if (userAccount.getAccount().equals(this) &&
            		userAccount.getUser().equals(user)) {
                iterator.remove();
                userAccount.getUser().getAccounts().remove(userAccount);
                userAccount.setAccount(null);
                userAccount.setUser(null);
            }
        }
    }
	
	public List<User> getListOfUsers(){
		List<User> listOfAccounts = new ArrayList<>();
		for(UserAccount uac: users) {
			listOfAccounts.add(uac.getUser());
		}
		return listOfAccounts;
	}
	
	public void generateIban() {
		Random rand = new Random();
	    String iban = "ES";
	    for (int i = 0; i < 14; i++){
	        int n = rand.nextInt(10) + 0;
	        iban += Integer.toString(n);
	    }
	    
	    this.iban = iban;
	}
	
	public boolean addBalance(double value) {
		this.balance += value;
		return true;
	}
	
	public boolean substractBalance(double value) {
		if(value > this.balance)
			return false;
		else 
			this.balance -= value;
		
		return true;
	}
	
	public User getAccountOwner() {
		for(UserAccount uac: users) {
			if(uac.getRoleId() == 0)
				return uac.getUser();
		}
		
		return null;
	}
	
	public String getFormattedBalance() {
		String balance = String.format("%.2f", this.balance);
		balance += " " + Utils.getCurrencySimbol(this.currency);
		
		return balance;
	}
	
	public String getFormattedIban() {
		
		StringBuffer formattedIban = new StringBuffer();
		
		for (int i = 0; i < 16; i++){
	        if(i % 4 == 0)
	          formattedIban.append(" ");
	        formattedIban.append(this.iban.charAt(i));
	    }
		
		return formattedIban.toString();
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
		Account other = (Account) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
