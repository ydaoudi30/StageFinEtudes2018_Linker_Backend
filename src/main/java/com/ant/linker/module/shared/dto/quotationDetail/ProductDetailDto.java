package com.ant.linker.module.shared.dto.quotationDetail;

import java.util.ArrayList;
import java.util.List;

import com.ant.linker.module.shared.dto.product.CharacteristicDto;

public class ProductDetailDto {

	private String	model;
	private String	wording;
	private String	manufacturer;
	private String brand;
	private String image;
	private List<CharacteristicDto> characteristics ;	
	
	
	public ProductDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDetailDto(String model, String wording, String manufacturer,String brand, String image) {
		super();
		this.model = model;
		this.wording = wording;
		this.manufacturer = manufacturer;
		this.brand= brand;
		this.image = image;
		this.characteristics = new ArrayList<>();
		
	}
	
	public String getModel(){
		return this.model;
	}
	public String getWording(){
		return this.wording;
	}
	public String getManufacturer(){
		return this.manufacturer;
	}
	public String getBrand(){
		return this.brand;
	}
	public String getImage(){
		return this.image;
	}
	
	public void setModel(String model){
		this.model = model;
	}
	public void setManufacturer(String manuf){
		this.manufacturer = manuf;
	}
	public void setBrand(String brand){
		this.brand = brand;
	}
	public void setWording(String wording){
		this.wording = wording;
	}
	public void setImage(String image){
		this.image = image;
	}
	
	public List<CharacteristicDto> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<CharacteristicDto> characteristics) {
		this.characteristics = characteristics;
	}
	
}