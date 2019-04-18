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
	
	

}
