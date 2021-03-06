package com.unileon.insoII.mgb.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "User")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String nombre;
	private String apellidos;
	private String dni;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;
	private Date birthdate;
	private String address;
	private String city;
	private String country;
	@Column(name="firstLogin", columnDefinition="BOOLEAN DEFAULT TRUE")
	private boolean firstLogin;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserAccount> accounts = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Card> cards = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Notification> notifications = new HashSet<>();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Set<UserAccount> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<UserAccount> userAccounts) {
		this.accounts = userAccounts;
	}
	public Set<Card> getCards() {
		return cards;
	}
	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}
	public Set<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	
	public int getRoleByAccount(Account ac) {
		for(UserAccount uac : accounts) {
			if(uac.getAccount().getId() == ac.getId())
				return uac.getRoleId();
		}
		
		return -1;
		
	}
	
	public List<Transaction> getAllTransactions(){
		
		List<Account> accounts = this.getListOfAccounts();
		List<Transaction> totalTransactions = new ArrayList<>();
		for(Account account: accounts) {
			Set<Transaction> trans = account.getTransactionsDone();
			for(Transaction tr : trans) {
				totalTransactions.add(tr);
			}
			trans = account.getTransactionsRecieved();
			for(Transaction tr : trans) {
				totalTransactions.add(tr);
			}
		}
		Comparator comparator = Collections.reverseOrder();
		
		Collections.sort(totalTransactions, new Comparator<Transaction>() {
			  public int compare(Transaction o1, Transaction o2) {
			      return o1.getTransactionDate().compareTo(o2.getTransactionDate());
			  }
		});
		Collections.reverse(totalTransactions);
		return totalTransactions;
	}
	
	public String getFullName() {
		
		String[] apellidosArray = this.apellidos.split(" ");
		String fullName = this.nombre;
		
		if(apellidosArray.length>1) {
			for(int i = 0; i<apellidosArray.length; i++) {
				fullName.concat(apellidosArray[i]);
				fullName.concat(" ");
			}
		}else {
			fullName += " " + this.apellidos;
		}
		
		return fullName;
	}
	
	public List<Account> getListOfAccounts(){
		List<Account> listOfAccounts = new ArrayList<>();
		for(UserAccount uac: accounts) {
			listOfAccounts.add(uac.getAccount());
		}
		return listOfAccounts;
	}
	
	public UserAccount getUserAccountByAccount(Account account) {
		System.out.println("SIZE: " + accounts.size());
		for(UserAccount ac : accounts) {
			System.out.println("A " + ac.getId() + " " + account.getId());
			if(ac.getAccount().getId() == account.getId()) {
				return ac;
			}
		}
		
		return null;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
}
