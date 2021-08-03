package com.ant.linker.module.shared.dto.cart;

import java.util.List;


public class CartDto {
	
	private String customer;
	
	private String guest;
	
	private List<CommercialCartDto> listCommercialCart;
	
	
	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CartDto(String customer, String guest,List<CommercialCartDto> listCommercialCart) {
		super();
		this.customer = customer;
		this.guest = guest;
		this.listCommercialCart = listCommercialCart;
	}


	


	public String getGuest() {
		return guest;
	}


	public void setGuest(String guest) {
		this.guest = guest;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	

	public List<CommercialCartDto> getListCommercialCartDto() {
		return listCommercialCart;
	}


	public void setListCommercialCartDto(List<CommercialCartDto> listCommercialCartDto) {
		this.listCommercialCart = listCommercialCartDto;
	}
	
	public void addCommercialCartDto(CommercialCartDto element){
		this.listCommercialCart.add(element);
	}
	
	public void removeCommercialCartDto(CommercialCartDto element){
		this.listCommercialCart.remove(element);
	}
	
}
