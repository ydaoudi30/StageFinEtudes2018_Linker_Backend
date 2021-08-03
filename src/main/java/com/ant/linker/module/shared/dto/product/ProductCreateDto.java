package com.ant.linker.module.shared.dto.product;

import java.util.ArrayList;
import java.util.List;

public class ProductCreateDto {
	
	private String model;
	private String label;
	private String description;
	private String manufacturer;
	private String brandLabel;
	private String refCategory;
	private String commercialEmail;
	private List<CharacteristicDto> characteristics ;	
	private List<String> images;

	public ProductCreateDto() {
		super();
		this.characteristics = new ArrayList<>();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getBrandLabel() {
		return brandLabel;
	}

	public void setBrandLabel(String brandLabel) {
		this.brandLabel = brandLabel;
	}

	public String getRefCategory() {
		return refCategory;
	}

	public void setRefCategory(String refCategory) {
		this.refCategory = refCategory;
	}

	public String getCommercialEmail() {
		return commercialEmail;
	}

	public void setCommercialEmail(String commercialEmail) {
		this.commercialEmail = commercialEmail;
	}

	public List<CharacteristicDto> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<CharacteristicDto> characteristics) {
		this.characteristics = characteristics;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
	
	public void addCharacteristic(CharacteristicDto characteristic) {
		this.characteristics.add(characteristic);
	}
	
}
