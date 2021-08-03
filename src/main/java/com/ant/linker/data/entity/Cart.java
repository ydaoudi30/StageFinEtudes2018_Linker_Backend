package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.SerializableString;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index

public class Cart implements Serializable {
	
	@Id
	private Long id;
	@Load
	private Ref<Guest> guest;
	@Load
	private Ref<Customer> customer;
	@Load
	private List<Ref<CommercialCart>> listCommercialCart;
	
	
	public Cart() {
		super();
		this.listCommercialCart = new ArrayList<>();
	}
	
	public void setGuest(Guest guest) {
		this.guest = Ref.create(guest);
	}
	
	public void setCustomer(Customer customer) {
		this.customer = Ref.create(customer);
	}
	
	public Guest getGuest() {
		return guest.get();
	}
	
	public Customer getCustomer() {
		return customer.get();
	}
	
	public void setListCommercialCart(List<CommercialCart> liste) {
		for (CommercialCart comCart : liste) {

			listCommercialCart.add(Ref.create(comCart));

		}

	}
	
	public List<CommercialCart> getListCommercialCart() {
		List<CommercialCart> commarcialCarts = new ArrayList<>();

		for (Ref<CommercialCart> comCart : listCommercialCart) {

			CommercialCart coCart = comCart.get();

			commarcialCarts.add(coCart);

		}

		return commarcialCarts;
	}
	
	public void addCommercialCart(CommercialCart commercialCart) {
		
		this.listCommercialCart.add(Ref.create(commercialCart));

	}
	
	public void removeCommercialCart(CommercialCart commercialCart){
		
		this.listCommercialCart.remove(Ref.create(commercialCart));
		
	}
	
	
}
