package com.unileon.insoII.mgb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Notification")
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String message;
	@ManyToOne
	@JoinColumn
	private User user;


}
