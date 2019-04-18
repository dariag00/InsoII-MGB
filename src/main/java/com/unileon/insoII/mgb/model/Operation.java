package com.unileon.insoII.mgb.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;


}
