package com.ant.linker.module.shared.dto.cart;

public class AddFromGuestCartToCartDto {
	
	private String brand;
	private String model;
	private String guestEmail;
	private String customerEmail;
	private String commercialEmail;
	
	
	public AddFromGuestCartToCartDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AddFromGuestCartToCartDto(String brand, String model, String guestEmail, String customerEmail, String commercialEmail) {
		super();
		this.brand = brand;
		this.model = model;
		this.guestEmail = guestEmail;
		this.customerEmail = customerEmail;
		this.commercialEmail = commercialEmail;
	}



	public String getCommercialEmail() {
		return commercialEmail;
	}


	public void setCommercialEmail(String commercialEmail) {
		this.commercialEmail = commercialEmail;
	}


	public String getGuestEmail() {
		return guestEmail;
	}


	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
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

	
	
	
	

}
