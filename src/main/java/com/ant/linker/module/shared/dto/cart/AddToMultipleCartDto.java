package com.ant.linker.module.shared.dto.cart;

import java.util.List;

public class AddToMultipleCartDto {
	
	private String brand;
	private String model;
	private String guestOrCustomerEmail;
	private List<String> commercialEmail;
	
	public AddToMultipleCartDto(String brand, String model, String guestOrCustomerEmail, List<String> commercialEmail) {
		super();
		this.brand = brand;
		this.model = model;
		this.guestOrCustomerEmail = guestOrCustomerEmail;
		this.commercialEmail = commercialEmail;
	}
	
	public AddToMultipleCartDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<String> getCommercialEmail() {
		return commercialEmail;
	}

	public void setCommercialEmail(List<String> commercialEmail) {
		this.commercialEmail = commercialEmail;
	}
}
