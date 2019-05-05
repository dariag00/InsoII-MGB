package com.unileon.insoII.mgb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
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

}
