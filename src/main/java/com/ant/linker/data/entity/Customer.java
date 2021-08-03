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

public class Customer implements Serializable {

	@Id
	private Long id;
	
	@Load
	private List<Ref<QuotationRequest>> listeQuotationRequest;
	@Load
	private Ref<User> userAccount;
	@Load
	private Ref<Cart> cart;

	public Customer() {
		super();
		this.listeQuotationRequest = new ArrayList<>();
	}

	public Customer(String firstName, String lastName, String email, String phone, String password) {
		this.listeQuotationRequest = new ArrayList<>();

	}

	public User getUserAccount() {
		return userAccount.get();
	}


	public void setUserAccount(User userAccount) {
		this.userAccount = Ref.create(userAccount);
	}

	public List<QuotationRequest> getListeQuotationRequest() {
		List<QuotationRequest> QuotationRequests = new ArrayList<>();

		for (Ref<QuotationRequest> refQuotationRequest : listeQuotationRequest) {

			QuotationRequest QuotationRequest = refQuotationRequest.get();

			QuotationRequests.add(QuotationRequest);

		}

		return QuotationRequests;
	}

	public void setlisteQuotationRequest(List<QuotationRequest> liste) {
		for (QuotationRequest QuotationRequest : liste) {

			this.listeQuotationRequest.add(Ref.create(QuotationRequest));

		}
	}

	public void addQuotationRequest(QuotationRequest quotationRequest) {

		this.listeQuotationRequest.add(Ref.create(quotationRequest));

	}
	public void removeQuotationrequest(QuotationRequest quotationRequest){
		
		this.listeQuotationRequest.remove(Ref.create(quotationRequest));
		
	}
	
	public Cart getCart() {
		return cart.get();
	}


	public void setCart(Cart cart) {
		this.cart = Ref.create(cart);
	}

}