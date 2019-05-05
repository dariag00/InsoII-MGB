package com.unileon.insoII.mgb.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User_Account")
public class UserAccount implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn
	private Account account;
	
	private int roleId;
	
	
	public UserAccount(User user, Account account) {
		this.user = user;
		this.account = account;
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


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
	

}
