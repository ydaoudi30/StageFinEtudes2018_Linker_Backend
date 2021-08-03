package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index
public class User implements Serializable {

	@Id
	private Long idUser;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private String company;
	
	@Load
	private List<UserRole> roles;
	@Load
	private Ref<Commercial> commercialProfil;
	@Load
	private Ref<Customer> customerProfil;
	@Load
	private Ref<Guest> guestProfil;
	
	@Load
	private List<Ref<Event>> events;
	
	public User() {
		super();
		this.roles = new ArrayList<>();
		this.events = new ArrayList<>();
	}
	
	public User(String firstName, String lastName, String email, String phone, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.roles = new ArrayList<>();
		this.events = new ArrayList<>();
	}

	public User(String firstName, String lastName, String email, String phone, String password, List<UserRole> userRoles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.roles = userRoles;
		this.events = new ArrayList<>();
	}
	
	public Customer getCustomerProfil() {
		return customerProfil.get();
	}

	public void setCustomerProfil(Customer customerProfil) {
		this.customerProfil = Ref.create(customerProfil);
	}
	
	public Guest getGuestProfil() {
		return guestProfil.get();
	}

	public void setGuestProfil(Guest guestProfil) {
		this.guestProfil = Ref.create(guestProfil);
	}

	public Commercial getCommercialProfil() {
		return commercialProfil.get();
	}

	public void setCommercialProfil(Commercial commercialProfil) {
		this.commercialProfil = Ref.create(commercialProfil);
	}

	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	
	public String getFullName(){
		return this.firstName + " " + this.lastName;
	}
	
	public void setFullName(String fullName){
		String[] parts = fullName.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}
	
	
	public void addUserRole(UserRole userRole){
		this.roles.add(userRole);
	}
	
	public void removeUserRole(UserRole userRole){
		this.roles.remove(userRole);
	}


	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
	
	public void addRoles(UserRole role) {
		this.roles.add(role);
	}
	
	public List<Event> getEvents() {
		List<Event> eventsResult = new ArrayList<>();
		for(Ref<Event> event : events) {
			eventsResult.add(event.get());
		}
		return eventsResult;
	}

	public void setEvents(List<Event> events) {
		this.events = new ArrayList<>();
		for(Event event : events) {
			this.events.add(Ref.create(event));
		}
	}
	
	public void addEvent(Event event) {
		
		this.events.add(Ref.create(event));

	}
	
	public void removeEvent(Event event){
		
		this.events.remove(Ref.create(event));
		
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
