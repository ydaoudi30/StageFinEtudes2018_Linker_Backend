package com.ant.linker.module.shared.dto.cart;

public class RemoveProductFromCartManager {
	private String model;
	private String brand;
	private String quoteRef;
	private String customer;
	private String commercial;
	
	
	public RemoveProductFromCartManager() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RemoveProductFromCartManager(String model, String brand, String quoteRef, String customer, String commercial) {
		super();
		this.model = model;
		this.brand = brand;
		this.quoteRef = quoteRef;
		this.customer = customer;
		this.commercial = commercial;
	}


	public String getCommercial() {
		return commercial;
	}


	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getQuoteRef() {
		return quoteRef;
	}


	public void setQuoteRef(String quoteRef) {
		this.quoteRef = quoteRef;
	}
	
	
}
