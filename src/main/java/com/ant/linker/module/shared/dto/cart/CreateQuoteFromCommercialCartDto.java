package com.ant.linker.module.shared.dto.cart;

public class CreateQuoteFromCommercialCartDto {
	
	private String customerEmail;
	
	private CommercialCartDto commercialCart;

	public CreateQuoteFromCommercialCartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateQuoteFromCommercialCartDto(String customerEmail, CommercialCartDto commercialCart) {
		super();
		this.customerEmail = customerEmail;
		this.commercialCart = commercialCart;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public CommercialCartDto getCommercialCart() {
		return commercialCart;
	}

	public void setCommercailCart(CommercialCartDto commercailCart) {
		this.commercialCart = commercailCart;
	}
	
	
	
}

