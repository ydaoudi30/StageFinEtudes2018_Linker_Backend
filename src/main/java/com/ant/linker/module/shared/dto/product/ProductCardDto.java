package com.ant.linker.module.shared.dto.product;

public class ProductCardDto {
	private Long id;
	private String label;
	private String model;
	private String brand;
	private String thumbnail;
	
	public ProductCardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductCardDto(Long id, String label, String model, String brand, String thumbnail) {
		super();
		this.id = id;
		this.label = label;
		this.model = model;
		this.brand = brand;
		this.thumbnail = thumbnail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
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
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
	
	
	
}
