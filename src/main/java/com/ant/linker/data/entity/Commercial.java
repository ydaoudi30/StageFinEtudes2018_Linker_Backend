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

public class Commercial implements Serializable {
	
	@Id
	private Long id;
	
	@Load
	private List<Ref<CommercialCatalog>> listCommercialCatalog;
	@Load
	private List<Ref<ModificationRequest>> listModificationRequest;
	@Load
	private List<Ref<QuotationRequest>> listQuotationRequest;
	@Load
	private List<Ref<CommercialCart>> listCommercialCart;
	@Load
	private Ref<User> userAccount;
	
	public User getUserAccount() {
		return userAccount.get();
	}


	public void setUserAccount(User userAccount) {
		this.userAccount = Ref.create(userAccount);
	}


	public void addModificationRequest(ModificationRequest modificationRequest) {

		listModificationRequest.add(Ref.create(modificationRequest));

	}
	
	
	public void removeModificationRequest(ModificationRequest modificationRequest){
		
		listModificationRequest.remove(Ref.create(modificationRequest));
		
	}
	public void addCommercialCatalog(CommercialCatalog commercialCatalog) {

		listCommercialCatalog.add(Ref.create(commercialCatalog));

	}

	
	public void removeCommercialCatalog(CommercialCatalog commercialCatalog) {

		listCommercialCatalog.remove(Ref.create(commercialCatalog));

	}

	public Commercial() {
		super();
		// TODO Auto-generated constructor stub
		this.listCommercialCatalog = new ArrayList<>();
		this.listModificationRequest = new ArrayList<>();
		this.listQuotationRequest = new ArrayList<>();
		this.listCommercialCart = new ArrayList<>();
	}

	public List<CommercialCatalog> getlistCommercialCatalog() {
		List<CommercialCatalog> commercialCatalogs = new ArrayList<>();

		for (Ref<CommercialCatalog> refComCat : listCommercialCatalog) {

			CommercialCatalog commercialCatalog = refComCat.get();

			commercialCatalogs.add(commercialCatalog);

		}
		return commercialCatalogs;

	}
	public void setListCommercialCatalog(List<CommercialCatalog> liste) {
		for (CommercialCatalog commercialCatalog : liste) {

			listCommercialCatalog.add(Ref.create(commercialCatalog));

		}
	}


	public List<ModificationRequest> getListeModificationRequest() {

		List<ModificationRequest> modificationRequests = new ArrayList<>();

		for (Ref<ModificationRequest> refModRqst : listModificationRequest) {

			ModificationRequest modificationRequest = refModRqst.get();

			modificationRequests.add(modificationRequest);

		}
		return modificationRequests;
	}

	public void setListeModificationRequest(List<ModificationRequest> liste) {
		for (ModificationRequest modificationRequest : liste) {

			listModificationRequest.add(Ref.create(modificationRequest));

		}

	}
	
	public List<QuotationRequest> getListeQuotationRequest() {
		List<QuotationRequest> QuotationRequests = new ArrayList<>();

		for (Ref<QuotationRequest> refQuotationRequest : listQuotationRequest) {

			QuotationRequest QuotationRequest = refQuotationRequest.get();

			QuotationRequests.add(QuotationRequest);

		}

		return QuotationRequests;
	}
	
	public void setlisteQuotationRequest(List<QuotationRequest> liste) {
		for (QuotationRequest QuotationRequest : liste) {

			this.listQuotationRequest.add(Ref.create(QuotationRequest));

		}
	}
	
	public void addQuotationRequest(QuotationRequest quotationRequest) {
		
		this.listQuotationRequest.add(Ref.create(quotationRequest));

	}
	
	public void removeQuotationrequest(QuotationRequest quotationRequest){
		
		listQuotationRequest.remove(Ref.create(quotationRequest));
		
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
