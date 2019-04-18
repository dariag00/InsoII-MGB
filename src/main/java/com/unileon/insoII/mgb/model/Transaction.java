package com.unileon.insoII.mgb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn
	private Account originAccount;
	@ManyToOne
	@JoinColumn
	private Account destinyAccount;
	private Date transactionDate;
	private int type;
	private double value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getOriginAccount() {
		return originAccount;
	}
	public void setOriginAccount(Account originAccount) {
		this.originAccount = originAccount;
	}
	public Account getDestinyAccount() {
		return destinyAccount;
	}
	public void setDestinyAccount(Account destinyAccount) {
		this.destinyAccount = destinyAccount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	

}
