package com.ant.linker.module.shared.dto.cart;

public class AddToCartDto {
	
	private String brand;
	private String model;
	private String guestOrCustomerEmail;
	private String commercialEmail;
	
	
	public AddToCartDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AddToCartDto(String brand, String model, String guestOrCustomerEmail, String commercialEmail) {
		super();
		this.brand = brand;
		this.model = model;
		this.guestOrCustomerEmail = guestOrCustomerEmail;
		this.commercialEmail = commercialEmail;
	}


	public String getCommercialEmail() {
		return commercialEmail;
	}


	public void setCommercialEmail(String commercialEmail) {
		this.commercialEmail = commercialEmail;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getGuestOrCustomerEmail() {
		return guestOrCustomerEmail;
	}


	public void setGuestOrCustomerEmail(String guestOrCustomerEmail) {
		this.guestOrCustomerEmail = guestOrCustomerEmail;
	}
	
	
	
	

}
