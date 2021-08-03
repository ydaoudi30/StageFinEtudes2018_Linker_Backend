package com.ant.linker.module.shared.dto.cart;

public class EmailsForMergeDto {
	

	private String guestEmail;
	private String customerEmail;
	
	
	public EmailsForMergeDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmailsForMergeDto(String guestEmail, String customerEmail) {
		super();
		this.guestEmail = guestEmail;
		this.customerEmail = customerEmail;
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
	

}
