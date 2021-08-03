package com.ant.linker.module.shared.dto.cart;

import java.util.List;

import com.ant.linker.module.shared.dto.product.ProductCreateDto;;

public class CommercialCartDto {
	
	private String commercial;
	private List<ProductCreateDto> listProduct;
	
	
	public CommercialCartDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CommercialCartDto(String commercial, List<ProductCreateDto> listProduct) {
		super();
		this.commercial = commercial;
		this.listProduct = listProduct;
	}


	public String getCommercial() {
		return commercial;
	}


	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}


	public List<ProductCreateDto> getListProduct() {
		return listProduct;
	}


	public void setListProduct(List<ProductCreateDto> listProduct) {
		this.listProduct = listProduct;
	}
	
	public void addProduct(ProductCreateDto element){
		this.listProduct.add(element);
	}
	
	public void removeProduct(ProductCreateDto element){
		this.listProduct.remove(element);
	}
	

}
