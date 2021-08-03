package com.ant.linker.module.shared.dto.product;

public class ProductSearchDto {
	private String model;
	private String brand;
	private String commercialEmail;

	public ProductSearchDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getCommercialEmail() {
		return commercialEmail;
	}

	public ProductSearchDto(String model, String brand, String commercialEmail) {
		super();
		this.model = model;
		this.brand = brand;
		this.commercialEmail = commercialEmail;
	}

	

	public void setCommercialEmail(String commercialEmail) {
		this.commercialEmail = commercialEmail;
	}
	
}
